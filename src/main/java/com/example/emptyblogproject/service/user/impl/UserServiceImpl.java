package com.example.emptyblogproject.service.user.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.emptyblogproject.bean.user.User;
import com.example.emptyblogproject.mapper.UsersMapper.UserMapper;
import com.example.emptyblogproject.service.user.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper , User> implements UserService {

    @Override
    public User getUserByEmail(String email) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("email" , email);
        User user = this.getOne(queryWrapper);
        return user;
    }

    @Override
    public Page<User> adminGetAllUserByPageAndCreateTime(int currentPage) {
        Page<User> page = new Page<>(currentPage , 9);
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("create_time");
        Page<User> userPage = this.page(page, queryWrapper);
        return userPage;
    }
}
