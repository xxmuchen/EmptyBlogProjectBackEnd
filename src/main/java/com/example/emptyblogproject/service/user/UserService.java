package com.example.emptyblogproject.service.user;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.emptyblogproject.bean.user.User;

public interface UserService extends IService<User> {

    /*通过邮箱获取用户*/
    public User getUserByEmail(String email);
}
