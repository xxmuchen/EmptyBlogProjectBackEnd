package com.example.emptyblogproject.controller.vlogcontroller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.emptyblogproject.annotation.UserLoginToken;
import com.example.emptyblogproject.bean.user.User;
import com.example.emptyblogproject.bean.vlog.Vlog;
import com.example.emptyblogproject.service.vlogservice.VlogService;
import com.example.emptyblogproject.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
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
    TokenUtils tokenUtils;


    @PostMapping("/addVlog")
    @UserLoginToken
    public String addVlog(@RequestBody String vlogData , HttpServletRequest httpServletRequest) {
        String authorization = httpServletRequest.getHeader("Authorization");
        User user = tokenUtils.parseTokenAndGetUser(authorization);

        JSONObject jsonObject = JSON.parseObject(vlogData);

        Vlog vlog = new Vlog();
        vlog.setAuthorId(user.getId());
        vlog.setAuthorAvatar(user.getAvatar());
        vlog.setAuthorName(user.getUserName());
        vlog.setVlogId(System.currentTimeMillis() + UUID.randomUUID().toString().replace("-" , ""));

        vlog.setSee(jsonObject.getBoolean("see"));
        vlog.setVideoUrl(jsonObject.getString("videoUrl"));
        vlog.setDescription(jsonObject.getString("description"));

        boolean flag = vlogService.save(vlog);

        if (flag) {
            return "上传成功";
        }else {
            throw new RuntimeException("上传失败，请重试");
        }
    }

    @PostMapping("/uploadVlogVideo")
    public String uploadVlogVideo(MultipartFile multipartFile) {

        return null;
    }
}
