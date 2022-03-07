package com.example.emptyblogproject.controller.usercontroller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.emptyblogproject.bean.user.User;
import com.example.emptyblogproject.service.user.UserService;
import com.example.emptyblogproject.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created with IntelliJ IDEA.
 * User: 王程翔
 * Date: 2022/3/7
 * Time: 18:25
 * Description:
 */
@RestController
public class UserSpaceController {

    @Autowired
    TokenUtils tokenUtils;
    @Autowired
    UserService userService;

    @PostMapping("/getUserByToken")
    public User getUserById(HttpServletRequest httpServletRequest) {
        String authorization = httpServletRequest.getHeader("Authorization");
        User user = tokenUtils.parseTokenAndGetUser(authorization);
        return user;
    }
    @PostMapping("updateUserInfo")
    public User updateUserInfoByToken(@RequestBody User user , HttpServletRequest httpServletRequest) {
        String authorization = httpServletRequest.getHeader("Authorization");
        User userData = tokenUtils.parseTokenAndGetUser(authorization);
        if (userData == null) {
            throw new RuntimeException("该用户未注册");
        }

        if (userData.getId().equals(user.getId())) {
            throw new RuntimeException("请勿攻击该系统");
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

        user.setId(userData.getId());

        boolean flag = userService.updateById(user);

        if (flag) {
            return userService.getById(user.getId());
        }else {
            throw new RuntimeException("更新失败，请重试");
        }
    }
}
