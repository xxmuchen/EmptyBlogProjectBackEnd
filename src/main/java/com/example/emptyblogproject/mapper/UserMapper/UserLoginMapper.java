package com.example.emptyblogproject.mapper.UserMapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.emptyblogproject.bean.user.UserLogin;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created with IntelliJ IDEA.
 * User: 王程翔
 * Date: 2022/3/15
 * Time: 1:54
 * Description:
 */
@Mapper
public interface UserLoginMapper extends BaseMapper<UserLogin> {
}
