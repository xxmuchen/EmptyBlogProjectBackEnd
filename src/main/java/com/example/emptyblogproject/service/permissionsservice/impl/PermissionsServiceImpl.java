package com.example.emptyblogproject.service.permissionsservice.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.emptyblogproject.bean.permissions.Permissions;
import com.example.emptyblogproject.mapper.permissionsmapper.PermissionsMapper;
import com.example.emptyblogproject.service.permissionsservice.PermissionsService;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * User: 王程翔
 * Date: 2022/3/13
 * Time: 18:23
 * Description:
 */
@Service
public class PermissionsServiceImpl extends ServiceImpl<PermissionsMapper , Permissions> implements PermissionsService {
    @Override
    public Permissions getPermissionByUserId(Long userId) {
        QueryWrapper<Permissions> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id" , userId);
        Permissions permissions = this.getOne(queryWrapper);
        return permissions;
    }
}
