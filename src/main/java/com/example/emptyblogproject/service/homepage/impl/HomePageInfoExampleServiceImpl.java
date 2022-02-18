package com.example.emptyblogproject.service.homepage.impl;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.emptyblogproject.bean.homepage.HomePageInfoExample;
import com.example.emptyblogproject.mapper.homepagemapper.HomePageInfoExampleMapper;
import com.example.emptyblogproject.service.homepage.HomePageInfoExampleService;
import org.springframework.stereotype.Service;

@Service
public class HomePageInfoExampleServiceImpl extends ServiceImpl<HomePageInfoExampleMapper , HomePageInfoExample> implements HomePageInfoExampleService {

}
