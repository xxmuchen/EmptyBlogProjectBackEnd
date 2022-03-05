package com.example.emptyblogproject.controller.griphiccontroller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.emptyblogproject.bean.griphic.Griphic;
import com.example.emptyblogproject.bean.user.User;
import com.example.emptyblogproject.bean.vlog.Vlog;
import com.example.emptyblogproject.service.griphicservice.GriphicService;
import com.example.emptyblogproject.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 * User: 王程翔
 * Date: 2022/3/4
 * Time: 10:14
 * Description:
 */
@RestController
public class GriphicController {

    @Autowired
    GriphicService griphicService;
    @Autowired
    TokenUtils tokenUtils;

    @Value("${file.GriphicImagePath}")
    String uploadVideoAbsolutePath;
    @GetMapping("getAllGriphicBySee")
    public List<Griphic> getAllGriphicBySee() {
        List<Griphic> griphicList = griphicService.getAllGriphicBySee();
        return griphicList;
    }
    @RequestMapping("/uploadGriphicImage")
    public String uploadGriphicImage(@RequestParam("griphicImageFile")  MultipartFile multipartFile) {
        if (multipartFile.isEmpty()) {
            throw new RuntimeException("文件不能为空");
        }
        String originalFilename = multipartFile.getOriginalFilename();

        File absolutePath = new File(uploadVideoAbsolutePath);
        if (!absolutePath.exists()) {
            absolutePath.mkdirs();
        }

        String imageFileName = "" + UUID.randomUUID() + UUID.randomUUID().hashCode() + originalFilename.substring(originalFilename.lastIndexOf("."));

        File dest = new File(absolutePath , imageFileName);
        try {
            multipartFile.transferTo(dest);

            return "http://localhost:8080/images/griphic/griphicImage/" + imageFileName;

        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("上传失败，请重试");
        }
    }

    @PostMapping("addGriphic")
    public String addGriphic(@RequestBody String griphicData , HttpServletRequest httpServletRequest) {
        String authorization = httpServletRequest.getHeader("Authorization");
        User user = tokenUtils.parseTokenAndGetUser(authorization);

        JSONObject jsonObject = JSON.parseObject(griphicData);

        Griphic griphic = new Griphic();
        griphic.setAuthorId(user.getId());
        griphic.setAuthorAvatar(user.getAvatar());
        griphic.setAuthorName(user.getUserName());
        griphic.setSee(jsonObject.getBoolean("see"));
        griphic.setImageUrl(jsonObject.getString("imageUrl"));
        griphic.setType(jsonObject.getString("type"));
        String description = jsonObject.getString("description");

        if ("".equals(description)) {
            description = null;
        }
        griphic.setDescription(description);

        boolean flag = griphicService.save(griphic);

        if (flag) {
            return "上传成功";
        }else {
            throw new RuntimeException("上传失败，请重试");
        }
    }

    @GetMapping("getGriphicById")
    public Griphic getGriphicById(@RequestParam("grophicId") Long grophicId) {
        Griphic griphic = griphicService.getById(grophicId);
        return griphic;
    }

    @GetMapping("getBuiltifulImageAndSentence")
    public List<Griphic> getBuiltifulImageAndSentence() {
        List<Griphic> griphicList = griphicService.getBuiltifulImageAndSentence();
        return griphicList;
    }
    @GetMapping("getHandWriteBeautifulSentence")
    public List<Griphic> getHandWriteBeautifulSentence() {
        List<Griphic> griphicList = griphicService.getHandWriteBeautifulSentence();
        return griphicList;
    }
    @GetMapping("getClassicDialogue")
    public List<Griphic> getClassicDialogue() {
        List<Griphic> griphicList = griphicService.getClassicDialogue();
        return griphicList;
    }
}
