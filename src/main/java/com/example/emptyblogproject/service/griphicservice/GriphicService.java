package com.example.emptyblogproject.service.griphicservice;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.emptyblogproject.bean.dairy.Diary;
import com.example.emptyblogproject.bean.datavisualization.bo.DataVisualizationBO;
import com.example.emptyblogproject.bean.griphic.Griphic;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: 王程翔
 * Date: 2022/3/4
 * Time: 10:13
 * Description:
 */
public interface GriphicService extends IService<Griphic> {

    public List<Griphic> getAllGriphicBySee();

    public List<Griphic> getBuiltifulImageAndSentence();

    public List<Griphic> getHandWriteBeautifulSentence();

    public List<Griphic> getClassicDialogue();

    public List<Griphic> getUserSpaceGriphicOrderCreateTime(Long userId);


    public List<Griphic> getUserSpaceGriphicUserStarOrderByCreateTime(Long userId);


    public List<Griphic> getUserSpaceGriphicUserCollectionOrderByCreateTime(Long userId);

    public List<Griphic> adminGetAllGriphicByPageAndCreateTime();

    public DataVisualizationBO getGriphicWriteAWeekDataVisualization();

    public DataVisualizationBO getGriphicStarAWeekDataVisualization();

    public DataVisualizationBO getGriphicCollectAWeekDataVisualization();

    public DataVisualizationBO getGriphicObserveAWeekDataVisualization();

    public List<Griphic> getUserSpaceGriphicStateSuccessOrderCreateTime(Long userId);

    public List<Griphic> getUserSpaceGriphicStateWaitOrderCreateTime(Long userId);

    public List<Griphic> getUserSpaceGriphicStateFailOrderCreateTime(Long userId);
}
