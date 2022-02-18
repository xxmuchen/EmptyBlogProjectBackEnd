package com.example.emptyblogproject.service.homepage;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.emptyblogproject.bean.homepage.HomePageInfo;
import com.example.emptyblogproject.mapper.homepagemapper.HomePageInfoContentMapper;
import com.example.emptyblogproject.mapper.homepagemapper.HomePageInfoExampleMapper;
import com.example.emptyblogproject.mapper.homepagemapper.HomePageInfoMapper;
import com.example.emptyblogproject.mapper.homepagemapper.HomePageInfoMediaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


public interface HomepageInfoService extends IService<HomePageInfo> {

}
