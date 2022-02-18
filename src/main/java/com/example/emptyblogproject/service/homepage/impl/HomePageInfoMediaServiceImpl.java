package com.example.emptyblogproject.service.homepage.impl;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.emptyblogproject.bean.homepage.HomePageInfoMedia;
import com.example.emptyblogproject.mapper.homepagemapper.HomePageInfoMediaMapper;
import com.example.emptyblogproject.service.homepage.HomePageInfoMediaService;
import org.springframework.stereotype.Service;

@Service
public class HomePageInfoMediaServiceImpl extends ServiceImpl<HomePageInfoMediaMapper , HomePageInfoMedia> implements HomePageInfoMediaService {
}
