package com.example.emptyblogproject.controller.usercontroller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.emptyblogproject.bean.user.User;
import com.example.emptyblogproject.service.user.UserService;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public Page<User> adminUpdateUserInfo(@RequestBody User user) {
        boolean flag = userService.updateById(user);
        if (flag) {
            Page<User> userPage = userService.adminGetAllUserByPageAndCreateTime(1);
            return userPage;
        }else {
            throw new RuntimeException("修改失败，请重试");
        }
    }
    @DeleteMapping("/adminDeleteUserById")
    public Page<User> adminDeleteUserById(@RequestParam(name = "userId") Long userId) {
        boolean flag = userService.removeById(userId);
        if (flag) {
            Page<User> userPage = userService.adminGetAllUserByPageAndCreateTime(1);
            return userPage;
        }else {
            throw new RuntimeException("删除失败，请重试");
        }
    }

}
