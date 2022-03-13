package com.example.emptyblogproject.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.example.emptyblogproject.bean.admin.Admin;
import com.example.emptyblogproject.bean.user.User;
import com.example.emptyblogproject.service.adminservice.AdminService;
import com.example.emptyblogproject.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Deprecated
public class AdminTokenUtils {

    @Autowired
    AdminService adminService;

    public String getToken(Admin admin) {
        Date start = new Date();
        long currentTime = System.currentTimeMillis() + 60* 60 * 1000;//一小时有效时间
//        long currentTime = System.currentTimeMillis() +  1000;//一小时有效时间
        Date end = new Date(currentTime);
        String token = "";

        token = JWT.create().withAudience(admin.getId().toString()).withIssuedAt(start).withExpiresAt(end)
                .sign(Algorithm.HMAC256(admin.getAdminPassword()));
        return token;
    }

//    解析token并获取user对象
    public Admin parseTokenAndGetAdmin(String authorization) {
        String userId = null;
        try {
            userId = JWT.decode(authorization).getAudience().get(0);
        } catch (JWTDecodeException j) {
            throw new RuntimeException("401");
        }
        Long admin_id = Long.parseLong(userId);
        Admin admin = adminService.getById(admin_id);
        return admin;
    }
}
