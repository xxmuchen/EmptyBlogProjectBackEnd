package com.example.emptyblogproject.service.userservice.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.emptyblogproject.bean.datavisualization.DataVisualizationDateAndInteger;
import com.example.emptyblogproject.bean.datavisualization.bo.DataVisualizationBO;
import com.example.emptyblogproject.bean.user.UserLogin;
import com.example.emptyblogproject.mapper.UserMapper.UserLoginMapper;
import com.example.emptyblogproject.service.userservice.UserLoginService;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: 王程翔
 * Date: 2022/3/15
 * Time: 1:54
 * Description:
 */
@Service
public class UserLoginServiceImpl extends ServiceImpl<UserLoginMapper , UserLogin> implements UserLoginService {
    @Override
    public DataVisualizationBO getUserLoginAWeekDataVisualization() {
        List<DataVisualizationDateAndInteger> userLoginAWeekDataVisualization = baseMapper.getUserLoginAWeekDataVisualization();
        System.out.println(userLoginAWeekDataVisualization);
        DataVisualizationBO dataVisualizationBO = new DataVisualizationBO();
        String strDateFormat = "yyyy-MM-dd";
        DateFormat dateFormat = new SimpleDateFormat(strDateFormat);

        for (DataVisualizationDateAndInteger dataVisualizationDateAndInteger: userLoginAWeekDataVisualization) {
            String format = dateFormat.format(dataVisualizationDateAndInteger.getTime());
            dataVisualizationBO.getXAxis().add(format);
            dataVisualizationBO.getYAxis().add(dataVisualizationDateAndInteger.getNumber());
        }
        return dataVisualizationBO;
    }
}
