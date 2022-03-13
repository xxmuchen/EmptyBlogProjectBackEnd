package com.example.emptyblogproject.service.adminservice;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.emptyblogproject.bean.admin.Admin;

/**
 * Created with IntelliJ IDEA.
 * User: 王程翔
 * Date: 2022/3/13
 * Time: 13:48
 * Description:
 */
@Deprecated
public interface AdminService extends IService<Admin> {

    /*通过手机号获取管理员信息*/
    public Admin getAdminInfoByAdminPhone(String adminPhone);
}
