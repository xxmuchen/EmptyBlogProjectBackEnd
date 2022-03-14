package com.example.emptyblogproject.service.user.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.emptyblogproject.bean.user.UserLogin;
import com.example.emptyblogproject.mapper.UserMapper.UserLoginMapper;
import com.example.emptyblogproject.service.user.UserLoginService;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * User: 王程翔
 * Date: 2022/3/15
 * Time: 1:54
 * Description:
 */
@Service
public class UserLoginServiceImpl extends ServiceImpl<UserLoginMapper , UserLogin> implements UserLoginService {
}
