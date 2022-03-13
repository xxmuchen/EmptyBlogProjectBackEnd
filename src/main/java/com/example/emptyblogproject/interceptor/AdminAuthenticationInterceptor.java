package com.example.emptyblogproject.interceptor;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.emptyblogproject.annotation.AdminLoginToken;
import com.example.emptyblogproject.annotation.AdminPassToken;
import com.example.emptyblogproject.annotation.UserPassToken;
import com.example.emptyblogproject.annotation.UserLoginToken;
import com.example.emptyblogproject.bean.admin.Admin;
import com.example.emptyblogproject.bean.user.User;
import com.example.emptyblogproject.service.adminservice.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * @author ：ZWQ
 * @version ：1.0
 * @date ：2019/10/16 - 18:47
 * @description ：拦截器去获取token并验证token
 */
@Deprecated
public class AdminAuthenticationInterceptor implements HandlerInterceptor {
    @Autowired
    AdminService adminService;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object object) throws Exception {
//        String token = httpServletRequest.getHeader("token");// 从 http 请求头中取出 token
        String token = httpServletRequest.getHeader("Authorization");// 从 http 请求头中取出 token
        // 如果不是映射到方法直接通过
//        System.out.println(object instanceof HandlerMethod);
        if (!(object instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) object;
        Method method = handlerMethod.getMethod();
        //检查是否有passtoken注释，有则跳过认证
        if (method.isAnnotationPresent(AdminPassToken.class)) {
            AdminPassToken adminPassToken = method.getAnnotation(AdminPassToken.class);
            if (adminPassToken.required()) {
                return true;
            }
        }
        //检查有没有需要用户权限的注解
        if (method.isAnnotationPresent(AdminLoginToken.class)) {
            AdminLoginToken adminLoginToken = method.getAnnotation(AdminLoginToken.class);
            if (adminLoginToken.required()) {
                // 执行认证
                if (token == null) {
                    throw new RuntimeException("无token，请重新登录");
                }
                // 获取 token 中的 usercontroller id
                String adminId = null;
//
                try {
                    adminId = JWT.decode(token).getAudience().get(0);
                } catch (JWTDecodeException j) {
                    throw new RuntimeException("402");
                }
                Long id = Long.parseLong(adminId);
                Admin admin = adminService.getById(id);
                if (admin == null) {
                    throw new RuntimeException("管理员不存在，请重新登录");
                }
                // 验证 token
                JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(admin.getAdminPassword())).build();
                try {
                    jwtVerifier.verify(token);
                } catch (JWTVerificationException e) {
                    throw new RuntimeException("402");
                }
                return true;
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest,
                           HttpServletResponse httpServletResponse,
                           Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest,
                                HttpServletResponse httpServletResponse,
                                Object o, Exception e) throws Exception {
    }
}