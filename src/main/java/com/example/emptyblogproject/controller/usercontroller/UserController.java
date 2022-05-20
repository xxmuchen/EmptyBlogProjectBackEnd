package com.example.emptyblogproject.controller.usercontroller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.emptyblogproject.annotation.UserLoginToken;
import com.example.emptyblogproject.bean.permissions.Permissions;
import com.example.emptyblogproject.bean.user.User;
import com.example.emptyblogproject.service.diaryservice.DiaryService;
import com.example.emptyblogproject.service.griphicservice.GriphicService;
import com.example.emptyblogproject.service.permissionsservice.PermissionsService;
import com.example.emptyblogproject.service.sentenceservice.SentenceService;
import com.example.emptyblogproject.service.userservice.UserService;

import com.example.emptyblogproject.service.vlogservice.VlogService;
import com.example.emptyblogproject.utils.IpUtils;
import com.example.emptyblogproject.utils.UserLoginUtils;
import com.example.emptyblogproject.utils.UserTokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
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
    @Autowired
    DiaryService diaryService;
    @Autowired
    SentenceService sentenceService;
    @Autowired
    VlogService vlogService;
    @Autowired
    GriphicService griphicService;

    @Autowired
    PermissionsService permissionsService;

    @Value("${file.avatarPath}")
    private String uploadAbsolutePath;


//    @Autowired
//    TokenService tokenService;

    @Autowired
    UserTokenUtils userTokenUtils;

    @Autowired
    UserLoginUtils userLoginUtils;

//    注册用户
    @PostMapping("/registUser")
    public String saveUser(@RequestBody User user) {
//        System.out.println(usercontroller);
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name", user.getUserName());
        List<User> list = userService.list(queryWrapper);
        if (list != null && list.size() != 0) {
            throw new RuntimeException("该昵称已被占用，请重新更换昵称");
        }else if (user == null || user.getUserName() == null || user.getUserName().equals("")) {
            throw new RuntimeException("您的昵称输入错误，请重试");
        }else if (user == null || user.getPassword() == null || user.getPassword().equals("") ||user.getPassword().length() < 6 || user.getPassword().length() > 20) {
            throw new RuntimeException("您的密码输入错误，请重试");
        }else if (user == null || user.getSex() == null || user.getSex().equals("")) {
            throw new RuntimeException("您的性别选择错误，请重试");
        }else if (user == null || user.getEmail() == null || user.getEmail().equals("") || !user.getEmail().contains("@")) {
            throw new RuntimeException("您的邮箱输入错误，请重试");
        }else if (user != null && user.getSynopsis().equals("")) {
            user.setSynopsis(null);
        }else if (user != null && user.getBirthday().equals("")) {
            user.setBirthday(null);
        }else if (user != null && user.getAvatar().equals("")) {
            user.setAvatar(null);
        }else if (user != null && user.getLocation().equals("")) {
            user.setLocation(null);
        }

//        System.out.println(userservice);
        boolean flag = userService.save(user);



//        System.out.println(userservice.getAvatar());
        if (flag) {
            User userByEmail = userService.getUserByEmail(user.getEmail());
            if (userByEmail == null) {
                throw new RuntimeException("注册失败，请联系后台管理员QQ邮箱：2879257224@qq.com");
            }
            Permissions permissions = new Permissions();
            permissions.setUserPermission(0);
            permissions.setUserId(userByEmail.getId());
            boolean save = permissionsService.save(permissions);
            if (save) {
                return "注册成功,马上为您转到登录界面";
            }else {
                throw new RuntimeException("注册失败，请联系后台管理员QQ邮箱：2879257224@qq.com");
            }

        }else {
            throw new RuntimeException("注册失败，请联系后台管理员QQ邮箱：2879257224@qq.com");
        }

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
            return "http://localhost:8080/images/User/UserAvatar/" + avatarFileName;
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("上传失败，请重试");
        }
    }

//    用户登录
    @PostMapping("/userLogin")
    public String userLogin(@RequestBody String userData , HttpServletRequest request) {
        JSONObject jsonObject = JSON.parseObject(userData);
        String email = (String) jsonObject.get("email");
        String password = (String) jsonObject.get("password");
        User user = userService.getOne(new QueryWrapper<User>().eq("email", email));
        if (user == null) {
            throw new RuntimeException("该用户不存在");
        }
        if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
            String ipAddr = IpUtils.getIpAddr(request);
            userLoginUtils.saveUserLoginInfo(user , "用户" , ipAddr);
            String token = userTokenUtils.getToken(user);
            System.out.println(token);
            return token;
        }else {
            throw new RuntimeException("密码错误，请重试");
        }
    }

//    获取用户名和头像
    @UserLoginToken
    @PostMapping("/getAvatarAndUserName")
    public Map<String , String> getAvatorAndUserName(HttpServletRequest httpServletRequest) {
        String authorization = httpServletRequest.getHeader("Authorization");
//        String userId = null;
//        try {
//            userId = JWT.decode(authorization).getAudience().get(0);
//        } catch (JWTDecodeException j) {
//            throw new RuntimeException("401");
//        }
//        Long user_id = Long.parseLong(userId);

        User user = userTokenUtils.parseTokenAndGetUser(authorization);

//        User userservice = userService.getById(user_id);
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

    /*获取User博客信息*/
    @GetMapping("/getUserBlogInfoByUserId")
    public Map<String , String> getUserBlogInfoByUserId(@RequestParam("userId") Long userId) {
        Map<String , String> userBlogInfoMap = new HashMap<>();
        User user = userService.getById(userId);
        userBlogInfoMap.put("userId" , user.getId() + "");
        userBlogInfoMap.put("userName" , user.getUserName());
        userBlogInfoMap.put("userSynopsis" , user.getSynopsis());
        userBlogInfoMap.put("userAvatar" , user.getAvatar());
        userBlogInfoMap.put("userDiaryCount"  , diaryService.getAllDiaryStateSuccessByUserId(userId).size() + "");
        userBlogInfoMap.put("userSentenceCount"  , sentenceService.getAllSentenceStateSuccessByUserId(userId).size() + "");
        userBlogInfoMap.put("userVlogCount"  , vlogService.getAllVlogStateSuccessByUserId(userId).size() + "");
        userBlogInfoMap.put("userGriphicCount"  , griphicService.getAllGriphicStateSuccessByUserId(userId).size() + "");

        return userBlogInfoMap;
//        userBlogInfoMap.put("")
    }

}
