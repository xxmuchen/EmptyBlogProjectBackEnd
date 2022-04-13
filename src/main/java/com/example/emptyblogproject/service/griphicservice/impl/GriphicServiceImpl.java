package com.example.emptyblogproject.service.griphicservice.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.emptyblogproject.bean.dairy.Diary;
import com.example.emptyblogproject.bean.datavisualization.DataVisualizationDateAndInteger;
import com.example.emptyblogproject.bean.datavisualization.bo.DataVisualizationBO;
import com.example.emptyblogproject.bean.griphic.Griphic;
import com.example.emptyblogproject.mapper.griphicmapper.GriphicMapper;
import com.example.emptyblogproject.service.griphicservice.GriphicService;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: 王程翔
 * Date: 2022/3/4
 * Time: 10:13
 * Description:
 */
@Service
public class GriphicServiceImpl extends ServiceImpl<GriphicMapper , Griphic> implements GriphicService {

    @Override
    public List<Griphic> getAllGriphicBySee() {
        QueryWrapper<Griphic> queryWrapper = new QueryWrapper();
        queryWrapper.eq("see" , true);
        queryWrapper.eq("state" , "审批通过");
        List<Griphic> griphicList = this.list(queryWrapper);
        return griphicList;
    }

    @Override
    public List<Griphic> getBuiltifulImageAndSentence() {
        QueryWrapper<Griphic> queryWrapper = new QueryWrapper();
        queryWrapper.eq("see" , true);
        queryWrapper.eq("state" , "审批通过");
        queryWrapper.eq("type" , "美图美句");
        List<Griphic> griphicList = this.list(queryWrapper);
        return griphicList;
    }

    @Override
    public List<Griphic> getHandWriteBeautifulSentence() {
        QueryWrapper<Griphic> queryWrapper = new QueryWrapper();
        queryWrapper.eq("see" , true);
        queryWrapper.eq("state" , "审批通过");
        queryWrapper.eq("type" , "手写美句");
        List<Griphic> griphicList = this.list(queryWrapper);
        return griphicList;
    }

    @Override
    public List<Griphic> getClassicDialogue() {
        QueryWrapper<Griphic> queryWrapper = new QueryWrapper();
        queryWrapper.eq("see" , true);
        queryWrapper.eq("state" , "审批通过");
        queryWrapper.eq("type" , "经典对白");
        List<Griphic> griphicList = this.list(queryWrapper);
        return griphicList;
    }

    @Override
    public List<Griphic> getUserSpaceGriphicOrderCreateTime(Long userId) {
//        Page<Griphic> page = new Page<>(currentPage , 7);
        QueryWrapper<Griphic> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("author_id" , userId);
        queryWrapper.orderByDesc("create_time");
//        Page<Griphic> diaryPage = this.page(page, queryWrapper);
        List<Griphic> griphicList = this.list(queryWrapper);
        return griphicList;
    }

    @Override
    public List<Griphic> getUserSpaceGriphicUserStarOrderByCreateTime(Long userId) {
//        Page<Griphic> page = new Page<>(currentPage , 7);
        List<Griphic> userSpaceGriphicUserStarOrderByCreateTime = baseMapper.getUserSpaceGriphicUserStarOrderByCreateTime(userId);
        return userSpaceGriphicUserStarOrderByCreateTime;
    }

    @Override
    public List<Griphic> getUserSpaceGriphicUserCollectionOrderByCreateTime(Long userId) {
        List<Griphic> userSpaceGriphicUserCollectionOrderByCreateTime = baseMapper.getUserSpaceGriphicUserCollectionOrderByCreateTime(userId);
        return userSpaceGriphicUserCollectionOrderByCreateTime;
    }

    @Override
    public List<Griphic> adminGetAllGriphicByPageAndCreateTime() {
        QueryWrapper<Griphic> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("state");
        queryWrapper.orderByDesc("create_time");
        List<Griphic> griphicList = this.list(queryWrapper);
        return griphicList;
    }

    @Override
    public DataVisualizationBO getGriphicWriteAWeekDataVisualization() {
        List<DataVisualizationDateAndInteger> griphicWriteAWeekDataVisualization = baseMapper.getGriphicWriteAWeekDataVisualization();
        System.out.println(griphicWriteAWeekDataVisualization);
        DataVisualizationBO dataVisualizationBO = new DataVisualizationBO();
        String strDateFormat = "yyyy-MM-dd";
        DateFormat dateFormat = new SimpleDateFormat(strDateFormat);
        for (DataVisualizationDateAndInteger dataVisualizationDateAndInteger: griphicWriteAWeekDataVisualization) {
            String format = dateFormat.format(dataVisualizationDateAndInteger.getTime());
            dataVisualizationBO.getXAxis().add(format);
            dataVisualizationBO.getYAxis().add(dataVisualizationDateAndInteger.getNumber());
        }
        return dataVisualizationBO;
    }

    @Override
    public DataVisualizationBO getGriphicStarAWeekDataVisualization() {
        List<DataVisualizationDateAndInteger> griphicStarAWeekDataVisualization = baseMapper.getGriphicStarAWeekDataVisualization();
        System.out.println(griphicStarAWeekDataVisualization);
        DataVisualizationBO dataVisualizationBO = new DataVisualizationBO();
        String strDateFormat = "yyyy-MM-dd";
        DateFormat dateFormat = new SimpleDateFormat(strDateFormat);
        for (DataVisualizationDateAndInteger dataVisualizationDateAndInteger: griphicStarAWeekDataVisualization) {
            String format = dateFormat.format(dataVisualizationDateAndInteger.getTime());
            dataVisualizationBO.getXAxis().add(format);
            dataVisualizationBO.getYAxis().add(dataVisualizationDateAndInteger.getNumber());
        }
        return dataVisualizationBO;
    }

    @Override
    public DataVisualizationBO getGriphicCollectAWeekDataVisualization() {
        List<DataVisualizationDateAndInteger> griphicCollectAWeekDataVisualization = baseMapper.getGriphicCollectAWeekDataVisualization();
        System.out.println(griphicCollectAWeekDataVisualization);
        DataVisualizationBO dataVisualizationBO = new DataVisualizationBO();
        String strDateFormat = "yyyy-MM-dd";
        DateFormat dateFormat = new SimpleDateFormat(strDateFormat);
        for (DataVisualizationDateAndInteger dataVisualizationDateAndInteger: griphicCollectAWeekDataVisualization) {
            String format = dateFormat.format(dataVisualizationDateAndInteger.getTime());
            dataVisualizationBO.getXAxis().add(format);
            dataVisualizationBO.getYAxis().add(dataVisualizationDateAndInteger.getNumber());
        }
        return dataVisualizationBO;
    }

    @Override
    public DataVisualizationBO getGriphicObserveAWeekDataVisualization() {
        List<DataVisualizationDateAndInteger> griphicObserveAWeekDataVisualization = baseMapper.getGriphicObserveAWeekDataVisualization();
        System.out.println(griphicObserveAWeekDataVisualization);
        DataVisualizationBO dataVisualizationBO = new DataVisualizationBO();
        String strDateFormat = "yyyy-MM-dd";
        DateFormat dateFormat = new SimpleDateFormat(strDateFormat);
        for (DataVisualizationDateAndInteger dataVisualizationDateAndInteger: griphicObserveAWeekDataVisualization) {
            String format = dateFormat.format(dataVisualizationDateAndInteger.getTime());
            dataVisualizationBO.getXAxis().add(format);
            dataVisualizationBO.getYAxis().add(dataVisualizationDateAndInteger.getNumber());
        }
        return dataVisualizationBO;
    }

    @Override
    public List<Griphic> getUserSpaceGriphicStateSuccessOrderCreateTime(Long userId) {
        QueryWrapper<Griphic> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("author_id" , userId);
        queryWrapper.eq("state" , "审批通过");
        queryWrapper.orderByDesc("create_time");
//        Page<Griphic> diaryPage = this.page(page, queryWrapper);
        List<Griphic> griphicList = this.list(queryWrapper);
        return griphicList;
    }

    @Override
    public List<Griphic> getUserSpaceGriphicStateWaitOrderCreateTime(Long userId) {
        QueryWrapper<Griphic> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("author_id" , userId);
        queryWrapper.eq("state" , "待审批");
        queryWrapper.orderByDesc("create_time");
//        Page<Griphic> diaryPage = this.page(page, queryWrapper);
        List<Griphic> griphicList = this.list(queryWrapper);
        return griphicList;
    }

    @Override
    public List<Griphic> getUserSpaceGriphicStateFailOrderCreateTime(Long userId) {
        QueryWrapper<Griphic> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("author_id" , userId);
        queryWrapper.eq("state" , "审批不通过");
        queryWrapper.orderByDesc("create_time");
//        Page<Griphic> diaryPage = this.page(page, queryWrapper);
        List<Griphic> griphicList = this.list(queryWrapper);
        return griphicList;
    }

    @Override
    public List<Griphic> getGriphicByKeyValue(String griphicKeyValue) {
        QueryWrapper<Griphic> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("state" , "审批通过");
        queryWrapper.eq("see" , true);
        List<Griphic> list = this.list(queryWrapper);

        List<Griphic> griphicList = new ArrayList<>();

        for (int i = 0 ; i < list.size() ; i++) {
            if (list.get(i).toString().contains(griphicKeyValue)) {
                griphicList.add(list.get(i));
            }
        }
        return griphicList;
    }
}
