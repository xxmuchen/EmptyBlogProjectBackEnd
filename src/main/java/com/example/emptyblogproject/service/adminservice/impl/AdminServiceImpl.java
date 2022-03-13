package com.example.emptyblogproject.service.adminservice.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.emptyblogproject.bean.admin.Admin;
import com.example.emptyblogproject.mapper.adminMapper.AdminMapper;
import com.example.emptyblogproject.service.adminservice.AdminService;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * User: 王程翔
 * Date: 2022/3/13
 * Time: 13:48
 * Description:
 */
@Service
@Deprecated
public class AdminServiceImpl extends ServiceImpl<AdminMapper , Admin> implements AdminService {


    @Override
    public Admin getAdminInfoByAdminPhone(String adminPhone) {
        return null;
    }
}
