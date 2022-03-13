package com.example.emptyblogproject.config;

//import com.example.emptyblogproject.interceptor.AuthenticationInterceptor;
import com.example.emptyblogproject.interceptor.AdminAuthenticationInterceptor;
import com.example.emptyblogproject.interceptor.UserAuthenticationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author ：ZWQ
 * @version ：1.0
 * @date ：2019/10/16 - 18:49
 * @description ：
 */

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(userAuthenticationInterceptor())
                // 拦截所有请求，通过判断是否有 @LoginRequired 注解 决定是否需要登录
                .addPathPatterns("/**");
//        registry.addInterceptor(adminAuthenticationInterceptor())
//                // 拦截所有请求，通过判断是否有 @LoginRequired 注解 决定是否需要登录
//                .addPathPatterns("/**");
    }
    @Bean
    public UserAuthenticationInterceptor userAuthenticationInterceptor() {
        return new UserAuthenticationInterceptor();
    }

//    @Bean
//    public AdminAuthenticationInterceptor adminAuthenticationInterceptor() {
//        return new AdminAuthenticationInterceptor();
//    }
}