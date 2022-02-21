package com.example.emptyblogproject.controller.user;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.emptyblogproject.annotation.UserLoginToken;
import com.example.emptyblogproject.bean.user.User;
import com.example.emptyblogproject.service.user.UserService;

import com.example.emptyblogproject.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.xml.transform.Result;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController

//@Api

//@RequestMapping("api")
public class UserController {

    @Autowired
    UserService userService;


    @Value("${file.avatarPath}")
    private String uploadAbsolutePath;

//    @Autowired
//    TokenService tokenService;

    @Autowired
    TokenUtils tokenUtils;

//    注册用户
    @PostMapping("/registUser")
    public String saveUser(@RequestBody User user) {
//        System.out.println(user);
        QueryWrapper<User> queryWrapper = new QueryWrapper<>(   );
        queryWrapper.eq("user_name", user.getUserName());
        List<User> list = userService.list(queryWrapper);
        if (list != null && list.size() != 0) {
            throw new RuntimeException("该昵称已被占用，请重新更换昵称");
        }

        if (user == null || user.getUserName() == null || user.getUserName().equals("")) {
            throw new RuntimeException("您的昵称输入错误，请重试");
        }

        if (user == null || user.getPassword() == null || user.getPassword().equals("") ||user.getPassword().length() < 6 || user.getPassword().length() > 20) {
            throw new RuntimeException("您的密码输入错误，请重试");
        }

        if (user == null || user.getSex() == null || user.getSex().equals("")) {
            throw new RuntimeException("您的性别选择错误，请重试");
        }
        if (user == null || user.getEmail() == null || user.getEmail().equals("") || !user.getEmail().contains("@")) {
            throw new RuntimeException("您的邮箱输入错误，请重试");
        }

        System.out.println(user);
        boolean flag = userService.save(user);
        System.out.println(user.getAvatar());
        if (flag) {

            return "注册成功,马上为您转到登录界面";
        }
//        System.out.println(user);

        return "注册失败，请联系后台管理员QQ邮箱：2879257224@qq.com";
    }

//    上传头像
    @RequestMapping("/avatarUpload")
    public String avatarUpload(@RequestParam("userAvatarFile") MultipartFile multipartFile) {
        if (multipartFile.isEmpty()) {
            throw new RuntimeException("文件不能为空");
        }
        String originalFilename = multipartFile.getOriginalFilename();

        File absolutePath = new File(uploadAbsolutePath);
        if (!absolutePath.exists()) {
            absolutePath.mkdirs();
        }

        String avatarFileName = "" + UUID.randomUUID() + UUID.randomUUID().hashCode() + originalFilename.substring(originalFilename.lastIndexOf("."));;

        File dest = new File(absolutePath , avatarFileName);
        try {
            multipartFile.transferTo(dest);
            return "http://localhost:8080/images/UserAvatar/" + avatarFileName;
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("上传失败，请重试");
        }
    }

//    用户登录
    @PostMapping("/userLogin")
    public String userLogin(@RequestBody String userData , HttpServletRequest request) {
        System.out.println(userData);
        JSONObject jsonObject = JSON.parseObject(userData);
        String userName = (String) jsonObject.get("userName");
        String password = (String) jsonObject.get("password");
//        System.out.println(userName + " " + password);

        User user = userService.getOne(new QueryWrapper<User>().eq("user_name", userName));

        if (user == null) {
//            return "该用户不存在";
            throw new RuntimeException("该用户不存在");
        }

        if (user.getUserName().equals(userName) && user.getPassword().equals(password)) {
//            String token = tokenService.getToken(user);
//            return token;
            String token = tokenUtils.getToken(user);
            System.out.println(token);
            return token;
        }else {
            throw new RuntimeException("密码错误，请重试");
//            return "";
        }
    }

//    获取用户名和头像

    @PostMapping("/getAvatarAndUserName")
    public Map<String , String> getAvatorAndUserName(HttpServletRequest httpServletRequest) {
        String authorization = httpServletRequest.getHeader("Authorization");
        String userId = null;
        try {
            userId = JWT.decode(authorization).getAudience().get(0);
        } catch (JWTDecodeException j) {
            throw new RuntimeException("401");
        }
        Long user_id = Long.parseLong(userId);
        User user = userService.getById(user_id);
        if (user == null) {
            throw new RuntimeException("token出错，请用户重新登陆");
        }
        Map<String , String> map = new HashMap<>();
        map.put("avatar" , user.getAvatar());
        map.put("userName" , user.getUserName());
        return map;
    }
    /*测试token  不登录没有token*/
    @UserLoginToken
    @GetMapping("/getMessage")
    public String getMessage(){
        return "你已通过验证";
    }
}
