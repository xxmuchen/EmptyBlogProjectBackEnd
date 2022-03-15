package com.example.emptyblogproject.utils;

import com.example.emptyblogproject.bean.user.User;
import com.example.emptyblogproject.bean.user.UserLogin;
import com.example.emptyblogproject.service.userservice.UserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 * User: 王程翔
 * Date: 2022/3/15
 * Time: 2:12
 * Description:
 */
@Component
public class UserLoginUtils {

    @Autowired
    private UserLoginService userLoginService;

    public void saveUserLoginInfo(User user , String identity , String ipAddr) {
        UserLogin userLogin = new UserLogin();
        userLogin.setUserId(user.getId());
        userLogin.setUserName(user.getUserName());
        userLogin.setUserIdentity(identity);
        userLogin.setIpAddress(ipAddr);
        userLoginService.save(userLogin);
    }
}
