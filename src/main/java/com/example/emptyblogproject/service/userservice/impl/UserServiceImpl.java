package com.example.emptyblogproject.service.userservice.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.emptyblogproject.bean.datavisualization.DataVisualizationDateAndInteger;
import com.example.emptyblogproject.bean.datavisualization.DataVisualizationStringAndInteger;
import com.example.emptyblogproject.bean.datavisualization.bo.DataVisualizationBO;
import com.example.emptyblogproject.bean.permissions.Permissions;
import com.example.emptyblogproject.bean.user.User;
import com.example.emptyblogproject.mapper.UserMapper.UserMapper;
import com.example.emptyblogproject.service.permissionsservice.PermissionsService;
import com.example.emptyblogproject.service.userservice.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper , User> implements UserService {

    @Autowired
    PermissionsService permissionsService;

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
        List<User> records = userPage.getRecords();

        for (int i = 0 ; i < records.size() ; i++) {
            Permissions permission = permissionsService.getPermissionByUserId(records.get(i).getId());
            if (permission.getUserPermission() != 0) {
                records.remove(records.get(i));
                i--;
            }
        }
        userPage.setRecords(records);
        return userPage;
    }

    @Override
    public DataVisualizationBO getUserRegisterAWeekDataVisualization() {
        List<DataVisualizationDateAndInteger> userRegisterAWeekDataVisualization = baseMapper.getUserRegisterAWeekDataVisualization();
        System.out.println(userRegisterAWeekDataVisualization);
        DataVisualizationBO dataVisualizationBO = new DataVisualizationBO();
        String strDateFormat = "yyyy-MM-dd";
        DateFormat dateFormat = new SimpleDateFormat(strDateFormat);
        for (DataVisualizationDateAndInteger dataVisualizationDateAndInteger: userRegisterAWeekDataVisualization) {
            String format = dateFormat.format(dataVisualizationDateAndInteger.getTime());
            dataVisualizationBO.getXAxis().add(format);
            dataVisualizationBO.getYAxis().add(dataVisualizationDateAndInteger.getNumber());
        }
        return dataVisualizationBO;
    }

    @Override
    public DataVisualizationBO getUserPlaceDataVisualization() {
        List<DataVisualizationStringAndInteger> userPlaceDataVisualization = baseMapper.getUserPlaceDataVisualization();
//        System.out.println(userRegisterAWeekDataVisualization);
        DataVisualizationBO dataVisualizationBO = new DataVisualizationBO();
//        String strDateFormat = "yyyy-MM-dd";
//        DateFormat dateFormat = new SimpleDateFormat(strDateFormat);
        for (DataVisualizationStringAndInteger dataVisualizationStringAndInteger: userPlaceDataVisualization) {
//            String format = dateFormat.format(dataVisualizationDateAndInteger.getTime());
            dataVisualizationBO.getXAxis().add(dataVisualizationStringAndInteger.getStr());
            dataVisualizationBO.getYAxis().add(dataVisualizationStringAndInteger.getNumber());
        }
        return dataVisualizationBO;
    }

    @Override
    public Page<User> adminGetAllManagerByPageAndCreateTime(int currentPage) {
        Page<User> page = new Page<>(currentPage , 9);
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("create_time");
        Page<User> userPage = this.page(page, queryWrapper);
        List<User> records = userPage.getRecords();

        for (int i = 0 ; i < records.size() ; i++) {
            Permissions permission = permissionsService.getPermissionByUserId(records.get(i).getId());
            if (permission.getUserPermission() != 1) {
                records.remove(records.get(i));
                i--;
            }
        }
        userPage.setRecords(records);
        return userPage;
    }

    @Override
    public Page<User> adminGetAllSuperManagerByPageAndCreateTime(int currentPage) {
        Page<User> page = new Page<>(currentPage , 9);
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("create_time");
        Page<User> userPage = this.page(page, queryWrapper);
        List<User> records = userPage.getRecords();

        for (int i = 0 ; i < records.size() ; i++) {
            Permissions permission = permissionsService.getPermissionByUserId(records.get(i).getId());
            if (permission.getUserPermission() != 2) {
                records.remove(records.get(i));
                i--;
            }
        }
        userPage.setRecords(records);
        return userPage;
    }
}
