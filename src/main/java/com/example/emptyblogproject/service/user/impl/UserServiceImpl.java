package com.example.emptyblogproject.service.user.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.emptyblogproject.bean.user.User;
import com.example.emptyblogproject.mapper.UsersMapper.UserMapper;
import com.example.emptyblogproject.service.user.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper , User> implements UserService {

}
