package com.example.emptyblogproject.controller.usercontroller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.emptyblogproject.bean.permissions.Permissions;
import com.example.emptyblogproject.bean.user.User;
import com.example.emptyblogproject.service.permissionsservice.PermissionsService;
import com.example.emptyblogproject.service.userservice.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created with IntelliJ IDEA.
 * User: 王程翔
 * Date: 2022/3/14
 * Time: 19:27
 * Description:
 */
@RestController
public class AdminUserController {
    @Autowired
    UserService userService;

    @Autowired
    PermissionsService permissionsService;

//    获取所有权限为0的用户
    @GetMapping("/adminGetAllUserByPageAndCreateTime")
    public Page<User> adminGetAllUserByPageAndCreateTime(@RequestParam(name = "currentPage") int currentPage) {
        Page<User> userPage = userService.adminGetAllUserByPageAndCreateTime(currentPage);

        return userPage;
    }
    @GetMapping("/adminGetUserById")
    public User adminGetDiaryById(@RequestParam(name = "userId") Long userId) {

        User user = userService.getById(userId);

        return user;
    }
    @PutMapping("/adminUpdateUserInfo")
    public String adminUpdateUserInfo(@RequestBody User user) {
        boolean flag = userService.updateById(user);
        if (flag) {
            return "用户信息修改成功";
        }else {
            return "用户信息修改失败";
        }
//        if (flag) {
//            Page<User> userPage = userService.adminGetAllUserByPageAndCreateTime(1);
//            return userPage;
//        }else {
//            throw new RuntimeException("修改失败，请重试");
//        }
    }
    @DeleteMapping("/adminDeleteUserById")
    public String adminDeleteUserById(@RequestParam(name = "userId") Long userId) {
        boolean flag = userService.removeById(userId);
        if (flag) {
            return "用户信息删除成功";
        }else {
            return "用户信息删除失败";
        }
//        if (flag && userIdentity.equals("管理员")) {
//            Page<User> userPage = userService.adminGetAllUserByPageAndCreateTime(1);
//            return userPage;
//        } else if (flag && userIdentity.equals("超级管理员")) {
//            Page<User> userPage = userService.adminGetAllSuperManagerByPageAndCreateTime(1);
//            return userPage;
//        } else {
//            throw new RuntimeException("删除失败，请重试");
//        }
    }

//    获取所有权限为1的用户
    @GetMapping("/adminGetAllManagerByPageAndCreateTime")
    public Page<User> adminGetAllManagerByPageAndCreateTime(@RequestParam(name = "currentPage") int currentPage) {
        Page<User> userPage = userService.adminGetAllManagerByPageAndCreateTime(currentPage);

        return userPage;
    }

//    获取所有权限为2的用户
    @GetMapping("/adminGetAllSuperManagerByPageAndCreateTime")
    public Page<User> adminGetAllSuperManagerByPageAndCreateTime(@RequestParam(name = "currentPage") int currentPage) {
        Page<User> userPage = userService.adminGetAllSuperManagerByPageAndCreateTime(currentPage);

        return userPage;
    }

    @PostMapping("/adminAddUser")
    public String adminAddUser(@RequestBody User user) {
        boolean flag = userService.save(user);
        if (flag) {
            User userByEmail = userService.getUserByEmail(user.getEmail());
            Permissions permissions = new Permissions();
            permissions.setUserPermission(0);
            permissions.setUserId(userByEmail.getId());
            boolean save = permissionsService.save(permissions);
            if (save) {
                return "添加用户成功";
            }else {
                return "添加用户失败";
            }
        } else {
            return "添加用户失败";
        }
    }
}
