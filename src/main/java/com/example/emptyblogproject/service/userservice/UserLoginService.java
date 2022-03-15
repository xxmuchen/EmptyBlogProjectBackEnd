package com.example.emptyblogproject.service.userservice;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.emptyblogproject.bean.datavisualization.bo.DataVisualizationBO;
import com.example.emptyblogproject.bean.user.UserLogin;

/**
 * Created with IntelliJ IDEA.
 * User: 王程翔
 * Date: 2022/3/15
 * Time: 1:54
 * Description:
 */
public interface UserLoginService extends IService<UserLogin> {

    public DataVisualizationBO getUserLoginAWeekDataVisualization();
}
