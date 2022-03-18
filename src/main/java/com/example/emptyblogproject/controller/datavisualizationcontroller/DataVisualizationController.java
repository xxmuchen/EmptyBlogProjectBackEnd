package com.example.emptyblogproject.controller.datavisualizationcontroller;

import com.example.emptyblogproject.bean.datavisualization.bo.DataVisualizationBO;
import com.example.emptyblogproject.service.diaryservice.DiaryService;
import com.example.emptyblogproject.service.griphicservice.GriphicService;
import com.example.emptyblogproject.service.sentenceservice.SentenceService;
import com.example.emptyblogproject.service.userservice.UserLoginService;
import com.example.emptyblogproject.service.userservice.UserService;
import com.example.emptyblogproject.service.vlogservice.VlogService;
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
    @Autowired
    DiaryService diaryService;
    @Autowired
    SentenceService sentenceService;
    @Autowired
    VlogService vlogService;
    @Autowired
    GriphicService griphicService;


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


    /*日记撰写可视化*/
    @GetMapping("/getDiaryWriteAWeekDataVisualization")
    public DataVisualizationBO getDiaryWriteAWeekDataVisualization() {
        DataVisualizationBO diaryWriteAWeekDataVisualization = diaryService.getDiaryWriteAWeekDataVisualization();
        return diaryWriteAWeekDataVisualization;
    }

    @GetMapping("/getDiaryStarAWeekDataVisualization")
    public DataVisualizationBO getDiaryStarAWeekDataVisualization() {
        DataVisualizationBO diaryStarAWeekDataVisualization = diaryService.getDiaryStarAWeekDataVisualization();
        return diaryStarAWeekDataVisualization;
    }

    @GetMapping("/getDiaryCollectAWeekDataVisualization")
    public DataVisualizationBO getDiaryCollectAWeekDataVisualization() {
        DataVisualizationBO diaryCollectAWeekDataVisualization = diaryService.getDiaryCollectAWeekDataVisualization();
        return diaryCollectAWeekDataVisualization;
    }
    /*日记点赞收藏可视化*/
//    @GetMapping("/getDiaryStarAndCollectAWeekDataVisualization")
//    public List<DataVisualizationBO> getDiaryStarAndCollectAWeekDataVisualization() {
//        DataVisualizationBO diaryStarAWeekDataVisualization = diaryService.getDiaryStarAWeekDataVisualization();
//        DataVisualizationBO diaryCollectAWeekDataVisualization = diaryService.getDiaryCollectAWeekDataVisualization();
//        List<DataVisualizationBO> diaryStarAndCollectAWeekDataVisualization = new ArrayList<>();
//        diaryStarAndCollectAWeekDataVisualization.add(diaryStarAWeekDataVisualization);
//        diaryStarAndCollectAWeekDataVisualization.add(diaryCollectAWeekDataVisualization);
//        return diaryStarAndCollectAWeekDataVisualization;
//    }

    /*日记评论可视化*/
    @GetMapping("/getDiaryObserveAWeekDataVisualization")
    public DataVisualizationBO getDiaryObserveAWeekDataVisualization() {
        DataVisualizationBO diaryObserveAWeekDataVisualization = diaryService.getDiaryObserveAWeekDataVisualization();
        return diaryObserveAWeekDataVisualization;
    }



    /*句子撰写可视化*/
    @GetMapping("/getSentenceWriteAWeekDataVisualization")
    public DataVisualizationBO getSentenceWriteAWeekDataVisualization() {
        DataVisualizationBO sentenceWriteAWeekDataVisualization = sentenceService.getSentenceWriteAWeekDataVisualization();
        return sentenceWriteAWeekDataVisualization;
    }

    @GetMapping("/getSentenceStarAWeekDataVisualization")
    public DataVisualizationBO getSentenceStarAWeekDataVisualization() {
        DataVisualizationBO sentenceStarAWeekDataVisualization = sentenceService.getSentenceStarAWeekDataVisualization();
        return sentenceStarAWeekDataVisualization;
    }

    @GetMapping("/getSentenceCollectAWeekDataVisualization")
    public DataVisualizationBO getSentenceCollectAWeekDataVisualization() {
        DataVisualizationBO sentenceCollectAWeekDataVisualization = sentenceService.getSentenceCollectAWeekDataVisualization();
        return sentenceCollectAWeekDataVisualization;
    }
    /*句子点赞收藏可视化*/
//    @GetMapping("/getSentenceStarAndCollectAWeekDataVisualization")
//    public List<DataVisualizationBO> getSentenceStarAndCollectAWeekDataVisualization() {
//        DataVisualizationBO sentenceStarAWeekDataVisualization = sentenceService.getSentenceStarAWeekDataVisualization();
//        DataVisualizationBO sentenceCollectAWeekDataVisualization = sentenceService.getSentenceCollectAWeekDataVisualization();
//        List<DataVisualizationBO>  sentenceStarAndCollectAWeekDataVisualization = new ArrayList<>();
//        sentenceStarAndCollectAWeekDataVisualization.add(sentenceStarAWeekDataVisualization);
//        sentenceStarAndCollectAWeekDataVisualization.add(sentenceCollectAWeekDataVisualization);
//        return sentenceStarAndCollectAWeekDataVisualization;
//    }

    /*句子评论可视化*/
    @GetMapping("/getSentenceObserveAWeekDataVisualization")
    public DataVisualizationBO getSentenceObserveAWeekDataVisualization() {
        DataVisualizationBO sentenceObserveAWeekDataVisualization = sentenceService.getSentenceObserveAWeekDataVisualization();
        return sentenceObserveAWeekDataVisualization;
    }

    /*Vlog撰写可视化*/
    @GetMapping("/getVlogWriteAWeekDataVisualization")
    public DataVisualizationBO getVlogWriteAWeekDataVisualization() {
        DataVisualizationBO vlogWriteAWeekDataVisualization = vlogService.getVlogWriteAWeekDataVisualization();
        return vlogWriteAWeekDataVisualization;
    }

    @GetMapping("/getVlogStarAWeekDataVisualization")
    public DataVisualizationBO getVlogStarAWeekDataVisualization() {
        DataVisualizationBO vlogStarAWeekDataVisualization = vlogService.getVlogStarAWeekDataVisualization();
        return vlogStarAWeekDataVisualization;
    }

    @GetMapping("/getVlogCollectAWeekDataVisualization")
    public DataVisualizationBO getVlogCollectAWeekDataVisualization() {
        DataVisualizationBO vlogCollectAWeekDataVisualization = vlogService.getVlogCollectAWeekDataVisualization();
        return vlogCollectAWeekDataVisualization;
    }
    /*Vlog点赞收藏可视化*/
//    @GetMapping("/getVlogStarAndCollectAWeekDataVisualization")
//    public List<DataVisualizationBO> getVlogStarAndCollectAWeekDataVisualization() {
//        DataVisualizationBO vlogStarAWeekDataVisualization = vlogService.getVlogStarAWeekDataVisualization();
//        DataVisualizationBO vlogCollectAWeekDataVisualization = vlogService.getVlogCollectAWeekDataVisualization();
//        List<DataVisualizationBO> vlogStarAndCollectAWeekDataVisualization = new ArrayList<>();
//        vlogStarAndCollectAWeekDataVisualization.add(vlogStarAWeekDataVisualization);
//        vlogStarAndCollectAWeekDataVisualization.add(vlogCollectAWeekDataVisualization);
//        return vlogStarAndCollectAWeekDataVisualization;
//    }

    /*Vlog评论可视化*/
    @GetMapping("/getVlogObserveAWeekDataVisualization")
    public DataVisualizationBO getVlogObserveAWeekDataVisualization() {
        DataVisualizationBO vlogObserveAWeekDataVisualization = vlogService.getVlogObserveAWeekDataVisualization();
        return vlogObserveAWeekDataVisualization;
    }

    /*图文撰写可视化*/
    @GetMapping("/getGriphicWriteAWeekDataVisualization")
    public DataVisualizationBO getGriphicWriteAWeekDataVisualization() {
        DataVisualizationBO griphicWriteAWeekDataVisualization = griphicService.getGriphicWriteAWeekDataVisualization();
        return griphicWriteAWeekDataVisualization;
    }

    @GetMapping("/getGriphicStarAWeekDataVisualization")
    public DataVisualizationBO getGriphicStarAWeekDataVisualization() {
        DataVisualizationBO griphicStarAWeekDataVisualization = griphicService.getGriphicStarAWeekDataVisualization();
        return griphicStarAWeekDataVisualization;
    }

    @GetMapping("/getGriphicCollectAWeekDataVisualization")
    public DataVisualizationBO getGriphicCollectAWeekDataVisualization() {
        DataVisualizationBO griphicCollectAWeekDataVisualization = griphicService.getGriphicCollectAWeekDataVisualization();
        return griphicCollectAWeekDataVisualization;
    }

    /*图文点赞收藏可视化*/
//    @GetMapping("/getGriphicStarAndCollectAWeekDataVisualization")
//    public List<DataVisualizationBO> getGriphicStarAndCollectAWeekDataVisualization() {
//        DataVisualizationBO griphicStarAWeekDataVisualization = griphicService.getGriphicStarAWeekDataVisualization();
//        DataVisualizationBO griphicCollectAWeekDataVisualization = griphicService.getGriphicCollectAWeekDataVisualization();
//        List<DataVisualizationBO> griphicStarAndCollectAWeekDataVisualization = new ArrayList<>();
//        griphicStarAndCollectAWeekDataVisualization.add(griphicStarAWeekDataVisualization);
//        griphicStarAndCollectAWeekDataVisualization.add(griphicCollectAWeekDataVisualization);
//        return griphicStarAndCollectAWeekDataVisualization;
//    }

    /*图文评论可视化*/
    @GetMapping("/getGriphicObserveAWeekDataVisualization")
    public DataVisualizationBO getGriphicObserveAWeekDataVisualization() {
        DataVisualizationBO griphicObserveAWeekDataVisualization = griphicService.getGriphicObserveAWeekDataVisualization();
        return griphicObserveAWeekDataVisualization;
    }

}
