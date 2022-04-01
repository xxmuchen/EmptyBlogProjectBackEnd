package com.example.emptyblogproject.controller.usercontroller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.emptyblogproject.annotation.UserLoginToken;
import com.example.emptyblogproject.bean.permissions.Permissions;
import com.example.emptyblogproject.bean.user.User;
import com.example.emptyblogproject.service.permissionsservice.PermissionsService;
import com.example.emptyblogproject.service.userservice.UserService;
import com.example.emptyblogproject.utils.IpUtils;
import com.example.emptyblogproject.utils.UserLoginUtils;
import com.example.emptyblogproject.utils.UserTokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created with IntelliJ IDEA.
 * User: 王程翔
 * Date: 2022/3/13
 * Time: 18:32
 * Description:
 */
@RestController
public class AdminController {

    @Autowired
    UserService userService;
    @Autowired
    PermissionsService permissionsService;
    @Autowired
    UserTokenUtils userTokenUtils;
    @Autowired
    UserLoginUtils userLoginUtils;

    @PostMapping("/adminLogin")
    public String adminLogin(@RequestBody String loginData , HttpServletRequest httpServletRequest) {
        JSONObject jsonObject = JSON.parseObject(loginData);
        String adminEmail = jsonObject.getString("adminEmail");
        String adminPassword = jsonObject.getString("adminPassword");

        User user = userService.getUserByEmail(adminEmail);
        if (user == null) {
            throw new RuntimeException("该管理员不存在，请重试");
        }else if (!user.getPassword().equals(adminPassword)) {
            throw new RuntimeException("管理员密码错误，请重试");
        }

        Permissions permissions = permissionsService.getPermissionByUserId(user.getId());
        if (permissions.getUserPermission() > 0) {
            String ipAddr = IpUtils.getIpAddr(httpServletRequest);
//            System.out.println(ipAddr);
            userLoginUtils.saveUserLoginInfo(user , permissions.getUserPermission() + "级管理员" , ipAddr);

            String token = userTokenUtils.getToken(user);
//            System.out.println(token);
            return token;
//            return "登录成功，即将为您跳转页面";
        }else {
            throw new RuntimeException("登录失败，您的权限不足");
        }

    }
    @PostMapping("/getAdminOwnInfo")
    @UserLoginToken
    public User getAdminOwnInfo(HttpServletRequest httpServletRequest) {
        String authorization = httpServletRequest.getHeader("authorization");
        User user = userTokenUtils.parseTokenAndGetUser(authorization);
        if (user == null) {
            throw new RuntimeException("token出错，请用户重新登陆");
        }
        return user;
    }

//    /*管理员添加用户*/
//    @PostMapping("/adminAddUser")
//    public

}
