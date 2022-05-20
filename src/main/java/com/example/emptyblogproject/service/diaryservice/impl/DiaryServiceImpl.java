package com.example.emptyblogproject.service.diaryservice.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.emptyblogproject.bean.dairy.Diary;
import com.example.emptyblogproject.bean.datavisualization.DataVisualizationDateAndInteger;
import com.example.emptyblogproject.bean.datavisualization.bo.DataVisualizationBO;
import com.example.emptyblogproject.bean.productionstar.ProductionStar;
import com.example.emptyblogproject.bean.sentence.Sentence;
import com.example.emptyblogproject.mapper.diarymapper.DiaryMapper;
import com.example.emptyblogproject.service.diaryservice.DiaryService;
import com.example.emptyblogproject.service.productioncollectionservice.ProductionCollectionService;
import com.example.emptyblogproject.service.productionstarservice.ProductionStarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class DiaryServiceImpl extends ServiceImpl<DiaryMapper , Diary> implements DiaryService {

//    @Autowired
//    DiaryMapper diaryMapper;
    @Autowired
    ProductionStarService productionStarService;
    @Autowired
    ProductionCollectionService productionCollectionService;

    @Override
    public List<Diary> getNewDiaryFourPieces() {
        QueryWrapper<Diary> queryWrapper = new QueryWrapper();
        queryWrapper.eq("see" , true);
        queryWrapper.eq("state" , "审批通过");
        queryWrapper.orderByDesc("create_time");
        queryWrapper.last("limit 0,4");

        List<Diary> diaryList = this.list(queryWrapper);

        return diaryList;
    }

    @Override
    public Page<Diary> getNewDiaryListPageing(int currentPage) {
        Page<Diary> page = new Page<>(currentPage , 10);
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("see" , true);
        queryWrapper.eq("state" , "审批通过");
        queryWrapper.orderByDesc("create_time");
        Page<Diary> pageData = this.page(page, queryWrapper);
        return pageData;
    }

    @Override
    public Page<Diary> getRecommendDiaryListPageing(int currentPage) {
        Page<Diary> page = new Page<>(currentPage , 10);
        QueryWrapper<Diary> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("see" , true);
//        queryWrapper.eq("state" , "审批通过");
        Page<Diary> recommendDiaryListPageing = baseMapper.getRecommendDiaryListPageing(page);
        return recommendDiaryListPageing;
    }

    @Override
    public Page<Diary> getTopGuestDiaryListPageing(int currentPage) {
        Page<Diary> page = new Page<>(currentPage , 10);
        Page<Diary> topGuestDiarylistPageing = baseMapper.getTopGuestDiaryListPageing(page);
        return topGuestDiarylistPageing;
    }


    @Override
    public Page<Diary> getUserSpaceDiaryOrderCreateTime(int currentPage  , Long userId) {
        Page<Diary> page = new Page<>(currentPage , 6);
        QueryWrapper<Diary> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("author_id" , userId);
        queryWrapper.orderByDesc("create_time");
        Page<Diary> diaryPage = this.page(page, queryWrapper);
        return diaryPage;
    }

    @Override
    public Page<Diary> getUserSpaceDiaryStateSuccessOrderCreateTime(int currentPage, Long userId) {
        Page<Diary> page = new Page<>(currentPage , 6);
        QueryWrapper<Diary> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("author_id" , userId);
        queryWrapper.eq("state" , "审批通过");
        queryWrapper.orderByDesc("create_time");
        Page<Diary> diaryPage = this.page(page, queryWrapper);
        return diaryPage;
    }

    @Override
    public Page<Diary> getUserSpaceDiaryStateWaitOrderCreateTime(int currentPage  , Long userId) {
        Page<Diary> page = new Page<>(currentPage , 6);
        QueryWrapper<Diary> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("author_id" , userId);
        queryWrapper.eq("state" , "待审批");
        queryWrapper.orderByDesc("create_time");
        Page<Diary> diaryPage = this.page(page, queryWrapper);
        return diaryPage;
    }

    @Override
    public Page<Diary> getUserSpaceDiaryStateFailOrderCreateTime(int currentPage  , Long userId) {
        Page<Diary> page = new Page<>(currentPage , 6);
        QueryWrapper<Diary> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("author_id" , userId);
        queryWrapper.eq("state" , "审批不通过");
        queryWrapper.orderByDesc("create_time");
        Page<Diary> diaryPage = this.page(page, queryWrapper);
        return diaryPage;
    }

    @Override
    public Page<Diary> getUserSpaceDiaryUserStarOrderByCreateTime(int currentPage , Long userId) {
        Page<Diary> page = new Page<>(currentPage , 6);
        Page<Diary> userSpaceDiaryUserStarOrderByCreateTime = baseMapper.getUserSpaceDiaryUserStarOrderByCreateTime(page, userId);
        return userSpaceDiaryUserStarOrderByCreateTime;
    }

    @Override
    public Page<Diary> getUserSpaceDiaryUserCollectionOrderByCreateTime(int currentPage, Long userId) {
        Page<Diary> page = new Page<>(currentPage , 6);
        Page<Diary> userSpaceDiaryUserCollectionOrderByCreateTime = baseMapper.getUserSpaceDiaryUserCollectionOrderByCreateTime(page, userId);
        return userSpaceDiaryUserCollectionOrderByCreateTime;
    }

    @Override
    public Page<Diary> adminGetAllDiaryByPageAndCreateTime(int currentPage) {
        Page<Diary> page = new Page<>(currentPage , 9);
        QueryWrapper<Diary> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("state");
        queryWrapper.orderByDesc("create_time");
        Page<Diary> diaryPage = this.page(page, queryWrapper);
        return diaryPage;
    }

    @Override
    public DataVisualizationBO getDiaryWriteAWeekDataVisualization() {
        List<DataVisualizationDateAndInteger> diaryWriteAWeekDataVisualization = baseMapper.getDiaryWriteAWeekDataVisualization();
        System.out.println(diaryWriteAWeekDataVisualization);
        DataVisualizationBO dataVisualizationBO = new DataVisualizationBO();
        String strDateFormat = "yyyy-MM-dd";
        DateFormat dateFormat = new SimpleDateFormat(strDateFormat);
        for (DataVisualizationDateAndInteger dataVisualizationDateAndInteger: diaryWriteAWeekDataVisualization) {
            String format = dateFormat.format(dataVisualizationDateAndInteger.getTime());
            dataVisualizationBO.getXAxis().add(format);
            dataVisualizationBO.getYAxis().add(dataVisualizationDateAndInteger.getNumber());
        }
        return dataVisualizationBO;
    }

    @Override
    public DataVisualizationBO getDiaryObserveAWeekDataVisualization() {
        List<DataVisualizationDateAndInteger> diaryObserveAWeekDataVisualization = baseMapper.getDiaryObserveAWeekDataVisualization();
        System.out.println(diaryObserveAWeekDataVisualization);
        DataVisualizationBO dataVisualizationBO = new DataVisualizationBO();
        String strDateFormat = "yyyy-MM-dd";
        DateFormat dateFormat = new SimpleDateFormat(strDateFormat);
        for (DataVisualizationDateAndInteger dataVisualizationDateAndInteger: diaryObserveAWeekDataVisualization) {
            String format = dateFormat.format(dataVisualizationDateAndInteger.getTime());
            dataVisualizationBO.getXAxis().add(format);
            dataVisualizationBO.getYAxis().add(dataVisualizationDateAndInteger.getNumber());
        }
        return dataVisualizationBO;
    }

    @Override
    public DataVisualizationBO getDiaryStarAWeekDataVisualization() {
        List<DataVisualizationDateAndInteger> diaryStarAWeekDataVisualization = baseMapper.getDiaryStarAWeekDataVisualization();
        System.out.println(diaryStarAWeekDataVisualization);
        DataVisualizationBO dataVisualizationBO = new DataVisualizationBO();
        String strDateFormat = "yyyy-MM-dd";
        DateFormat dateFormat = new SimpleDateFormat(strDateFormat);
        for (DataVisualizationDateAndInteger dataVisualizationDateAndInteger: diaryStarAWeekDataVisualization) {
            String format = dateFormat.format(dataVisualizationDateAndInteger.getTime());
            dataVisualizationBO.getXAxis().add(format);
            dataVisualizationBO.getYAxis().add(dataVisualizationDateAndInteger.getNumber());
        }
        return dataVisualizationBO;
    }

    @Override
    public DataVisualizationBO getDiaryCollectAWeekDataVisualization() {
        List<DataVisualizationDateAndInteger> diaryCollectAWeekDataVisualization = baseMapper.getDiaryCollectAWeekDataVisualization();
        System.out.println(diaryCollectAWeekDataVisualization);
        DataVisualizationBO dataVisualizationBO = new DataVisualizationBO();
        String strDateFormat = "yyyy-MM-dd";
        DateFormat dateFormat = new SimpleDateFormat(strDateFormat);
        for (DataVisualizationDateAndInteger dataVisualizationDateAndInteger: diaryCollectAWeekDataVisualization) {
            String format = dateFormat.format(dataVisualizationDateAndInteger.getTime());
            dataVisualizationBO.getXAxis().add(format);
            dataVisualizationBO.getYAxis().add(dataVisualizationDateAndInteger.getNumber());
        }
        return dataVisualizationBO;
    }

    @Override
    public List<Diary> getDiaryByKeyValue(String diaryKeyValue) {
        QueryWrapper<Diary> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("state" , "审批通过");
        queryWrapper.eq("see" , true);
        List<Diary> list = this.list(queryWrapper);

        List<Diary> diaryList = new ArrayList<>();

        for (int i = 0 ; i < list.size() ; i++) {
            if (list.get(i).toString().contains(diaryKeyValue)) {
                diaryList.add(list.get(i));
            }
        }
        return diaryList;
    }

    @Override
    public List<Diary> getAllDiaryStateSuccessByUserId(Long userId) {
        QueryWrapper<Diary> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("state" , "审批通过");
        queryWrapper.eq("author_id" , userId);
        List<Diary> diaryList = this.list(queryWrapper);
        return diaryList;
    }

//    @Override
//    public Page<Diary> getAllStateWaitDiary(int currentPage) {
//        Page<Diary> page = new Page<>(currentPage , 9);
//        QueryWrapper<Diary> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("state" , "待审批");
//        queryWrapper.eq("del" , 0);
//        return this.page(page, queryWrapper);
//    }
//
//    @Override
//    public Page<Diary> getAllStateSuccessDiary(int currentPage) {
//        Page<Diary> page = new Page<>(currentPage , 9);
//        QueryWrapper<Diary> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("state" , "审批通过");
//        queryWrapper.eq("del" , 0);
//        return this.page(page, queryWrapper);
//    }
//
//    @Override
//    public Page<Diary> getAllStateFailDiary(int currentPage) {
//        Page<Diary> page = new Page<>(currentPage , 9);
//        QueryWrapper<Diary> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("state" , "审批不通过");
//        queryWrapper.eq("del" , 0);
//        return this.page(page, queryWrapper);
//    }


}
