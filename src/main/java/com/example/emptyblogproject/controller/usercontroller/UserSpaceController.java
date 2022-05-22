package com.example.emptyblogproject.controller.usercontroller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.emptyblogproject.bean.user.User;
import com.example.emptyblogproject.service.userservice.UserService;
import com.example.emptyblogproject.utils.UserTokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
    UserTokenUtils userTokenUtils;
    @Autowired
    UserService userService;

    @PostMapping("/getUserByToken")
    public User getUserById(HttpServletRequest httpServletRequest) {
        String authorization = httpServletRequest.getHeader("Authorization");
        User user = userTokenUtils.parseTokenAndGetUser(authorization);
        return user;
    }
    @PostMapping("/updateUserInfo")
    public User updateUserInfoByToken(@RequestBody User user , HttpServletRequest httpServletRequest) {
        String authorization = httpServletRequest.getHeader("Authorization");
        User userData = userTokenUtils.parseTokenAndGetUser(authorization);
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

    @PostMapping("/updateUserAvatar")
    public String updateUserAvatar(@RequestBody String avatarData , HttpServletRequest httpServletRequest) {
        JSONObject jsonObject = JSON.parseObject(avatarData);
        String avatar = jsonObject.getString("avatar");
        String authorization = httpServletRequest.getHeader("Authorization");
        User user = userTokenUtils.parseTokenAndGetUser(authorization);
        user.setAvatar(avatar);
        boolean flag = userService.updateById(user);
        if (flag) {
            return "头像更新成功";
        }else {
            throw new RuntimeException("头像更新失败");
        }
    }

    @PutMapping("/updateUserSpaceColor")
    public Boolean updateUserSpaceColor(@RequestBody String spaceColor , HttpServletRequest httpServletRequest) {
        String authorization = httpServletRequest.getHeader("Authorization");
        JSONObject jsonObject = JSON.parseObject(spaceColor);
        User user = userTokenUtils.parseTokenAndGetUser(authorization);
        user.setMySpaceColor(jsonObject.getString("mySpaceColorStyle"));
        boolean flag = userService.updateById(user);
        return flag;
    }
}
