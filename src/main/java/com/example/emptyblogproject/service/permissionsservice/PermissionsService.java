package com.example.emptyblogproject.service.permissionsservice;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.emptyblogproject.bean.permissions.Permissions;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created with IntelliJ IDEA.
 * User: 王程翔
 * Date: 2022/3/13
 * Time: 18:22
 * Description:
 */

public interface PermissionsService extends IService<Permissions> {
    /*通过用户Id获取权限*/
    public Permissions getPermissionByUserId(Long userId);
}
