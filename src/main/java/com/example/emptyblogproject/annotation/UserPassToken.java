package com.example.emptyblogproject.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author ：ZWQ
 * @version ：1.0
 * @date ：2019/10/16 - 18:44
 * @description ：用来跳过验证的PassToken
 */
@Target({ElementType.METHOD , ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface UserPassToken {
    boolean required() default true;
}
