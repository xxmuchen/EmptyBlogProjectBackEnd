package com.example.emptyblogproject.controller.homepagecontroller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.emptyblogproject.bean.homepage.HomePageInfo;
import com.example.emptyblogproject.bean.homepage.HomePageInfoContent;
import com.example.emptyblogproject.bean.homepage.HomePageInfoExample;
import com.example.emptyblogproject.bean.homepage.HomePageInfoMedia;
import com.example.emptyblogproject.service.homepage.HomePageInfoContentService;
import com.example.emptyblogproject.service.homepage.HomePageInfoExampleService;
import com.example.emptyblogproject.service.homepage.HomePageInfoMediaService;
import com.example.emptyblogproject.service.homepage.HomepageInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
//@CrossOrigin
public class HomePageInfoController {

//    @Autowired
//    homepageInfoService homepageInfoService;
//    @Autowired
//    homePageInfoContentService homePageInfoContentService;
//    @Autowired
//    homePageInfoExampleService homePageInfoExampleService;
//    @Autowired
//    homePageInfoMediaService homePageInfoMediaService;

    @Autowired
    HomepageInfoService homepageInfoService;
    @Autowired
    HttpServletRequest httpServletRequest;
    @Autowired
    HomePageInfoMediaService homePageInfoMediaService;
    @Autowired
    HomePageInfoContentService homePageInfoContentService;
    @Autowired
    HomePageInfoExampleService homePageInfoExampleService;



    @GetMapping("/index")
    @ResponseBody
//    @CrossOrigin
    public List<HomePageInfo> index(){
        List<HomePageInfo> homePageAllData = getHomePageAllData();
//        System.out.println(homePageAllData.size());
        return homePageAllData;
    }
//    获取首页全部数据
    public List<HomePageInfo> getHomePageAllData() {
        List<HomePageInfo> homePageInfoList = new ArrayList<>();

        QueryWrapper<HomePageInfo> homePageInfoQueryWrapper = new QueryWrapper<>();

        QueryWrapper<HomePageInfoContent> homePageInfoContentQueryWrapper = new QueryWrapper<>();
        QueryWrapper<HomePageInfoExample> homePageInfoExampleQueryWrapper = new QueryWrapper<>();
        QueryWrapper<HomePageInfoMedia> homePageInfoMediaQueryWrapper = new QueryWrapper<>();

        homePageInfoQueryWrapper.select("id");
//        homepageInfoService.getObj()
        List<Object> idList = homepageInfoService.listObjs(homePageInfoQueryWrapper);
        //            创建homePageInfo对象
        HomePageInfo homePageInfo = null;

        for (int i = 0 ; i < idList.size() ; i++) {
            int id = (int) idList.get(i);
//            清空queryWrapper
            homePageInfoQueryWrapper.clear();
            homePageInfoContentQueryWrapper.clear();
            homePageInfoExampleQueryWrapper.clear();
            homePageInfoMediaQueryWrapper.clear();
            //            创建homePageInfo对象
            homePageInfo = new HomePageInfo();
            //            添加id
            homePageInfo.setId(id);
//            查找type类型
            homePageInfoQueryWrapper.select("type").eq("id" , id);
            List<Object> typeList = homepageInfoService.listObjs(homePageInfoQueryWrapper);
            homePageInfo.setType((String) typeList.get(0));
//            获取url
            homePageInfoQueryWrapper.select("url");
            List<Object> UrlList = homepageInfoService.listObjs(homePageInfoQueryWrapper);
            homePageInfo.setUrl((String) UrlList.get(0));

            //            查找homePageInfoContent对象列表
            homePageInfoContentQueryWrapper.eq("home_page_info_id" , id);
            List<HomePageInfoContent> homePageInfoContentList = homePageInfoContentService.list(homePageInfoContentQueryWrapper);
            homePageInfo.setHomePageInfoContent(homePageInfoContentList);
            //            查找homePageInfoExample对象列表
            homePageInfoExampleQueryWrapper.eq("home_page_info_id" , id);
            List<HomePageInfoExample> homePageInfoExampleList = homePageInfoExampleService.list(homePageInfoExampleQueryWrapper);
            homePageInfo.setHomePageInfoExample(homePageInfoExampleList);
//            查找homePageInfoMedia对象列表
            homePageInfoMediaQueryWrapper.eq("home_page_info_id" , id);
            List<HomePageInfoMedia> homePageInfoMediaList = homePageInfoMediaService.list(homePageInfoMediaQueryWrapper);
            homePageInfo.setHomePageInfoMedia(homePageInfoMediaList);
            homePageInfoList.add(homePageInfo);
        }
        return homePageInfoList;
    }

//    获取首页数据
    @GetMapping("/HomePageDisplay")
    @ResponseBody
    public HomePageInfo getHomePageDisplayData() {
        List<HomePageInfo> homePageAllData = getHomePageAllData();
//        System.out.println(homePageAllData.get(0).getHomePageInfoMedia().size());
//        httpServletRequest.getRequestURI().toString()
        return homePageAllData.get(0);
    }
//    获取首页日记数据
    @GetMapping("/HomePageDiaryDisplay")
    @ResponseBody
    public HomePageInfo getHomePageDiaryDisplayData() {
        List<HomePageInfo> homePageAllData = getHomePageAllData();

//        httpServletRequest.getRequestURI().toString()
        return homePageAllData.get(1);
    }
//    获取首页句子数据
    @GetMapping("/HomePageSentenceDisplay")
    @ResponseBody
    public HomePageInfo getHomePageSentenceDisplayData() {

        List<HomePageInfo> homePageAllData = getHomePageAllData();
//        httpServletRequest.getRequestURI().toString()
        return homePageAllData.get(2);
    }
//    获取首页Vlog数据
    @GetMapping("/HomePageVlogDisplay")
    @ResponseBody
    public HomePageInfo getHomePageVlogDisplayData() {
        List<HomePageInfo> homePageAllData = getHomePageAllData();
//        httpServletRequest.getRequestURI().toString()
        return homePageAllData.get(3);
    }
//    获取首页图文数据
    @GetMapping("/HomePageGraphicDisplay")
    @ResponseBody
    public HomePageInfo getHomePageGraphicDisplayData() {
        List<HomePageInfo> homePageAllData = getHomePageAllData();
//        httpServletRequest.getRequestURI().toString()
        return homePageAllData.get(4);
    }

}
