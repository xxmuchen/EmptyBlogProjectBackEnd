package com.example.emptyblogproject.controller.vlogcontroller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.emptyblogproject.annotation.UserLoginToken;
import com.example.emptyblogproject.bean.sensitivewords.SensitiveWords;
import com.example.emptyblogproject.bean.user.User;
import com.example.emptyblogproject.bean.vlog.Vlog;
import com.example.emptyblogproject.service.sensitivewordsservice.SensitiveWordsService;
import com.example.emptyblogproject.service.vlogservice.VlogService;
import com.example.emptyblogproject.utils.UserTokenUtils;
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
 * Date: 2022/2/27
 * Time: 15:37
 * Description:
 */
@RestController
public class VlogController {

    @Autowired
    VlogService vlogService;
    @Autowired
    UserTokenUtils userTokenUtils;
    @Value("${file.vlogVideoPath}")
    private String uploadVideoAbsolutePath;
    @Autowired
    SensitiveWordsService sensitiveWordsService;


    @PostMapping("/addVlog")
    @UserLoginToken
    public String addVlog(@RequestBody String vlogData , HttpServletRequest httpServletRequest) {
        String authorization = httpServletRequest.getHeader("Authorization");
        User user = userTokenUtils.parseTokenAndGetUser(authorization);

        JSONObject jsonObject = JSON.parseObject(vlogData);

        Vlog vlog = new Vlog();
        vlog.setAuthorId(user.getId());
        vlog.setAuthorAvatar(user.getAvatar());
        vlog.setAuthorName(user.getUserName());
        vlog.setVlogId(System.currentTimeMillis() + UUID.randomUUID().toString().replace("-" , ""));

        vlog.setTitle(jsonObject.getString("title"));
        vlog.setSee(jsonObject.getBoolean("see"));
        vlog.setVideoUrl(jsonObject.getString("videoUrl"));
        vlog.setDescription(jsonObject.getString("description"));

        List<SensitiveWords> sensitiveWordsList = sensitiveWordsService.list();
        for (SensitiveWords sensitiveWords : sensitiveWordsList) {
            if (vlog.getTitle().contains(sensitiveWords.getSensitiveWord())) {
                throw new RuntimeException("该Vlog标题中含有敏感词:" + sensitiveWords.getSensitiveWord() + ",请修改后重新上传");
            }else if (vlog.getDescription().contains(sensitiveWords.getSensitiveWord())) {
                throw new RuntimeException("该Vlog描述中含有敏感词:" + sensitiveWords.getSensitiveWord() + ",请修改后重新上传");
            }
        }

        vlog.setState("待审批");
        boolean flag = vlogService.save(vlog);

        if (flag) {
            return "上传成功";
        }else {
            throw new RuntimeException("上传失败，请重试");
        }
    }

    @RequestMapping("/uploadVlogVideo")
    public String uploadVlogVideo(@RequestParam("vlogVideoFile") MultipartFile multipartFile) {
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

            return "http://localhost:8080/images/vlog/vlogVideo/" + imageFileName;

        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("上传失败，请重试");
        }
    }

//    获取所有可见视频
    @GetMapping("/getAllVlog")
    public List<Vlog> getAllVlog() {
        List<Vlog> vlogList = vlogService.getAllVlogBySee();
        return vlogList;
    }

    @GetMapping("/getVlogById")
    public Vlog getVlogById(@RequestParam(name = "vlogId") Long vlogId) {
        Vlog vlog = vlogService.getById(vlogId);
        return vlog;
    }
}
