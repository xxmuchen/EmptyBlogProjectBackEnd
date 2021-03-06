package com.example.emptyblogproject;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.emptyblogproject.bean.dairy.Diary;
import com.example.emptyblogproject.bean.homepage.HomePageInfo;
import com.example.emptyblogproject.bean.homepage.HomePageInfoContent;
import com.example.emptyblogproject.bean.homepage.HomePageInfoExample;
import com.example.emptyblogproject.bean.homepage.HomePageInfoMedia;
import com.example.emptyblogproject.bean.observe.observeBo.ObserveNodeBO;
import com.example.emptyblogproject.bean.user.User;
import com.example.emptyblogproject.mapper.UserMapper.UserMapper;
import com.example.emptyblogproject.mapper.diarymapper.DiaryMapper;
import com.example.emptyblogproject.mapper.homepagemapper.HomePageInfoContentMapper;
import com.example.emptyblogproject.mapper.homepagemapper.HomePageInfoExampleMapper;
import com.example.emptyblogproject.mapper.homepagemapper.HomePageInfoMapper;
import com.example.emptyblogproject.mapper.homepagemapper.HomePageInfoMediaMapper;
import com.example.emptyblogproject.mapper.observemapper.ObserveMapper;
import com.example.emptyblogproject.service.diaryservice.DiaryService;
import com.example.emptyblogproject.service.productionstarservice.ProductionStarService;
import com.example.emptyblogproject.service.homepage.HomepageInfoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.*;

@SpringBootTest
class EmptyBlogProjectApplicationTests {

    @Autowired
    HomePageInfoMapper homePageInfoMapper;
    @Autowired
    HomePageInfoContentMapper homePageInfoContentMapper;
    @Autowired
    HomePageInfoExampleMapper homePageInfoExampleMapper;
    @Autowired
    HomePageInfoMediaMapper homePageInfoMediaMapper;

    @Autowired
    HomepageInfoService homepageInfoService;

    @Autowired
    ProductionStarService productionStarService;

    @Autowired
    ObserveMapper observeMapper;

    @Autowired
    DiaryService diaryService;

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Autowired
    RedisTemplate<Object, User> userRedisTemplate;

    @Test
    public void userRedisTest() {
        User user = new User();
        user.setAvatar("??????");
        user.setUserName("??????");
        user.setEmail("??????");
        userRedisTemplate.opsForValue().set("user" , user);
    }

    @Test
    public void test7() {
        stringRedisTemplate.opsForValue().append("123" , "456");
//        stringRedisTemplate.delete("123");
        String s = stringRedisTemplate.opsForValue().get("123");
        System.out.println(s);
    }


    @Test
    public void test6() {
        Random random = new Random();
        System.out.println(random.nextInt(12));
    }

    @Test
    public void test5() {
        Page<Diary> recommendDiaryListPageing = diaryService.getRecommendDiaryListPageing(1);
        System.out.println(recommendDiaryListPageing.getRecords().size());
    }



    @Test
    public void test4() {
        List<ObserveNodeBO> observeNodeBOS = observeMapper.queryFirstObserveList("1", 1L);
//        System.out.println(observeNodeBOS);

        List<ObserveNodeBO> observeNodeBOS1 = observeMapper.querySecondObserveList("1", 1L);
        System.out.println(observeNodeBOS1);
    }


    @Test
    public void test3(){
        System.out.println(productionStarService.reLikeDiary(10L));
    }



    @Test
    public void getHomepageInfo() {
        QueryWrapper<HomePageInfo> homePageInfoQueryWrapper = new QueryWrapper<>();
        homePageInfoQueryWrapper.select("id");
        List<Object> list = homepageInfoService.listObjs(homePageInfoQueryWrapper);
        System.out.println(list);
    }
//    @Autowired
//    QueryWrapper<HomePageInfo> homePageInfoQueryWrapper;
//    @Autowired
//    QueryWrapper<HomePageInfoContent> HomePageInfoContentQueryWrapper;
//    @Autowired
//    QueryWrapper<HomePageInfoExample> HomePageInfoExampleQueryWrapper;
//    @Autowired
//    QueryWrapper<HomePageInfoMedia> HomePageInfoMediaQueryWrapper;
    @Test
    /*??????????????????*/
    void contextLoads() {
        QueryWrapper<HomePageInfo> homePageInfoQueryWrapper = new QueryWrapper<>();
        QueryWrapper<HomePageInfoContent> homePageInfoContentQueryWrapper = new QueryWrapper<>();
        QueryWrapper<HomePageInfoExample> homePageInfoExampleQueryWrapper = new QueryWrapper<>();
        QueryWrapper<HomePageInfoMedia> homePageInfoMediaQueryWrapper = new QueryWrapper<>();
        homePageInfoQueryWrapper.select("id");
        List<Object> idList = homePageInfoMapper.selectObjs(homePageInfoQueryWrapper);
        List<HomePageInfo> homePageInfoList = new ArrayList<>();

        HomePageInfo homePageInfo = null;
        for (Object id : idList){
//            ??????queryWrapper
            homePageInfoQueryWrapper.clear();
            homePageInfoContentQueryWrapper.clear();
            homePageInfoExampleQueryWrapper.clear();
            homePageInfoMediaQueryWrapper.clear();
//            ??????homePageInfo??????
            homePageInfo = new HomePageInfo();
//            ??????type??????
            homePageInfoQueryWrapper.select("type").eq("id" , id);
            List<Object> typeList = homePageInfoMapper.selectObjs(homePageInfoQueryWrapper);

            homePageInfo.setType((String) typeList.get(0));
//            ??????homePageInfoContent????????????
            homePageInfoContentQueryWrapper.eq("home_page_info_id" , id);
            List<HomePageInfoContent> homePageInfoContentList = homePageInfoContentMapper.selectList(homePageInfoContentQueryWrapper);

            homePageInfo.setHomePageInfoContent(homePageInfoContentList);
//            ??????homePageInfoExample????????????
            homePageInfoExampleQueryWrapper.eq("home_page_info_id" , id);
            List<HomePageInfoExample> homePageInfoExampleList = homePageInfoExampleMapper.selectList(homePageInfoExampleQueryWrapper);
            homePageInfo.setHomePageInfoExample(homePageInfoExampleList);
//            ??????homePageInfoMedia????????????
            homePageInfoMediaQueryWrapper.eq("home_page_info_id" , id);
            List<HomePageInfoMedia> homePageInfoMediaList = homePageInfoMediaMapper.selectList(homePageInfoMediaQueryWrapper);
            homePageInfo.setHomePageInfoMedia(homePageInfoMediaList);
            homePageInfoList.add(homePageInfo);
        }
        System.out.println(homePageInfoList.toString());
    }
    /*??????????????????*/

    @Autowired
    DiaryMapper diaryPageInfoMapper;
    @Test
    void diaryData() {
        List<Diary> diaryPageInfos = diaryPageInfoMapper.selectList(null);
//        System.out.println(diaryPageInfos);
        for (Diary d: diaryPageInfos) {
//            System.out.println(d);
        }
    }

    @Autowired
    UserMapper userMapper;

    @Test
    public void test1() {
//        User usercontroller = new User();
//        usercontroller.setUserName("?????????");
//        userMapper.update();
    }
}
