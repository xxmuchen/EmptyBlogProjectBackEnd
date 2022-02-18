package com.example.emptyblogproject.service.homepage.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.emptyblogproject.bean.homepage.HomePageInfo;
import com.example.emptyblogproject.mapper.homepagemapper.HomePageInfoMapper;
import com.example.emptyblogproject.service.homepage.HomepageInfoService;
import org.springframework.stereotype.Service;

@Service("homePageInfoService")
public class HomePageInfoServiceImpl extends ServiceImpl<HomePageInfoMapper , HomePageInfo>  implements HomepageInfoService{
}
