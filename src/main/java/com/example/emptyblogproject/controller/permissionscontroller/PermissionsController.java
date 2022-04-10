package com.example.emptyblogproject.controller.permissionscontroller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.emptyblogproject.bean.permissions.Permissions;
import com.example.emptyblogproject.bean.user.User;
import com.example.emptyblogproject.service.permissionsservice.PermissionsService;
import com.example.emptyblogproject.service.userservice.UserService;
import com.example.emptyblogproject.utils.UserTokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class PermissionsController {

    @Autowired
    PermissionsService permissionsService;
    @Autowired
    UserTokenUtils userTokenUtils;
    @Autowired
    UserService userService;

    @PostMapping("/getUserPermissionByUserId")
    public Permissions getUserPermissionByUserId (HttpServletRequest httpServletRequest) {
        String authorization = httpServletRequest.getHeader("authorization");
        User user = userTokenUtils.parseTokenAndGetUser(authorization);
        Permissions permission = permissionsService.getPermissionByUserId(user.getId());
        return permission;
    }
    @PostMapping("/setUserPermissionByUserId")
    public String setSimpleUserByUserId(@RequestBody String userData) {
        JSONObject jsonObject = JSON.parseObject(userData);
        Permissions permission = permissionsService.getPermissionByUserId(jsonObject.getLong("userId"));
        if (jsonObject.getInteger("permission") == 0) {
            permission.setUserPermission(0);
        } else if (jsonObject.getInteger("permission") == 1) {
            permission.setUserPermission(1);
        } else if (jsonObject.getInteger("permission") == 2) {
            permission.setUserPermission(2);
        }

        boolean flag = permissionsService.updateById(permission);
        if (flag) {
            return "权限修改成功";
        }else {
            return "权限修改失败";
        }
//        Page<User> userPage = null;
//        userPage = userService.adminGetAllUserByPageAndCreateTime(1);
//        return userPage;
    }
    @PostMapping("/setManagerByUserId")
    public Page<User> setManagerByUserId(@RequestBody String userData) {
        JSONObject jsonObject = JSON.parseObject(userData);
        Permissions permission = permissionsService.getPermissionByUserId(jsonObject.getLong("userId"));
        permission.setUserPermission(1);
        permissionsService.updateById(permission);
        Page<User> userPage = userService.adminGetAllUserByPageAndCreateTime(1);
        return userPage;
    }
    @PostMapping("/setSuperManagerByUserId")
    public Page<User> setSuperManagerByUserId(@RequestBody String userData) {
        JSONObject jsonObject = JSON.parseObject(userData);
        Permissions permission = permissionsService.getPermissionByUserId(jsonObject.getLong("userId"));
        permission.setUserPermission(2);
        permissionsService.updateById(permission);
        Page<User> userPage = userService.adminGetAllUserByPageAndCreateTime(1);
        return userPage;
    }
}