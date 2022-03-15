package com.example.emptyblogproject.controller.datavisualizationcontroller;

import com.example.emptyblogproject.bean.datavisualization.bo.DataVisualizationBO;
import com.example.emptyblogproject.service.userservice.UserLoginService;
import com.example.emptyblogproject.service.userservice.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: 王程翔
 * Date: 2022/3/15
 * Time: 22:07
 * Description:
 */
@RestController
public class DataVisualizationController {
    /*用户注册登录可视化显示*/
    @Autowired
    UserLoginService userLoginService;
    @Autowired
    UserService userService;

    /*获取最近一周登录和注册的可视化数据*/
    @GetMapping("/getUserLoginAWeekDataVisualization")
    public List<DataVisualizationBO> getUserLoginAWeekDataVisualization(){
        DataVisualizationBO userLoginAWeekDataVisualization = userLoginService.getUserLoginAWeekDataVisualization();
        DataVisualizationBO userRegisterAWeekDataVisualization = userService.getUserRegisterAWeekDataVisualization();
        List<DataVisualizationBO> dataVisualizationBOList = new ArrayList<>();
        dataVisualizationBOList.add(userLoginAWeekDataVisualization);
        dataVisualizationBOList.add(userRegisterAWeekDataVisualization);
        return dataVisualizationBOList;
    }

    /*获取所有用户居住的省市可视化数据*/
    @GetMapping("/getUserPlaceDataVisualization")
    public DataVisualizationBO getUserPlaceDataVisualization() {
        DataVisualizationBO userPlaceDataVisualization = userService.getUserPlaceDataVisualization();
        return userPlaceDataVisualization;
    }
}
