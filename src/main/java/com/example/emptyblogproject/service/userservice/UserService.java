package com.example.emptyblogproject.service.userservice;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.emptyblogproject.bean.datavisualization.bo.DataVisualizationBO;
import com.example.emptyblogproject.bean.user.User;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

public interface UserService extends IService<User> {

    /*通过邮箱获取用户*/
    public User getUserByEmail(String email);

    public Page<User> adminGetAllUserByPageAndCreateTime(int currentPage);

//    用户注册数据可视化显示
    public DataVisualizationBO getUserRegisterAWeekDataVisualization();

    public DataVisualizationBO getUserPlaceDataVisualization();
}
