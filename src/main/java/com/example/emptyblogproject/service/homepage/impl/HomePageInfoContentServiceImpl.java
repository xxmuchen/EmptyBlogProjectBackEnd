package com.example.emptyblogproject.service.homepage.impl;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.emptyblogproject.bean.homepage.HomePageInfo;
import com.example.emptyblogproject.bean.homepage.HomePageInfoContent;
import com.example.emptyblogproject.mapper.homepagemapper.HomePageInfoContentMapper;
import com.example.emptyblogproject.service.homepage.HomePageInfoContentService;
import org.springframework.stereotype.Service;

@Service
public class HomePageInfoContentServiceImpl extends ServiceImpl<HomePageInfoContentMapper , HomePageInfoContent> implements HomePageInfoContentService {
}
