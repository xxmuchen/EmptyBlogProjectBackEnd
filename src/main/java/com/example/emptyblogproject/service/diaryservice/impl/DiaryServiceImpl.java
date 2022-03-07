package com.example.emptyblogproject.service.diaryservice.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.emptyblogproject.bean.dairy.Diary;
import com.example.emptyblogproject.bean.productionstar.ProductionStar;
import com.example.emptyblogproject.mapper.diarymapper.DiaryMapper;
import com.example.emptyblogproject.service.diaryservice.DiaryService;
import com.example.emptyblogproject.service.productioncollectionservice.ProductionCollectionService;
import com.example.emptyblogproject.service.productionstarservice.ProductionStarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        queryWrapper.orderByDesc("create_time");
        Page<Diary> pageData = this.page(page, queryWrapper);
        return pageData;
    }

    @Override
    public Page<Diary> getRecommendDiaryListPageing(int currentPage) {
        Page<Diary> page = new Page<>(currentPage , 10);
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
        Page<Diary> page = new Page<>(currentPage , 7);
        QueryWrapper<Diary> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("author_id" , userId);
        queryWrapper.orderByDesc("create_time");
        Page<Diary> diaryPage = this.page(page, queryWrapper);
        return diaryPage;
    }

    @Override
    public Page<Diary> getUserSpaceDiaryUserStarOrderByCreateTime(int currentPage , Long userId) {
        Page<Diary> page = new Page<>(currentPage , 7);
        Page<Diary> userSpaceDiaryUserStarOrderByCreateTime = baseMapper.getUserSpaceDiaryUserStarOrderByCreateTime(page, userId);
        return userSpaceDiaryUserStarOrderByCreateTime;
    }

    @Override
    public Page<Diary> getUserSpaceDiaryUserCollectionOrderByCreateTime(int currentPage, Long userId) {
        Page<Diary> page = new Page<>(currentPage , 7);
        Page<Diary> userSpaceDiaryUserCollectionOrderByCreateTime = baseMapper.getUserSpaceDiaryUserCollectionOrderByCreateTime(page, userId);
        return userSpaceDiaryUserCollectionOrderByCreateTime;
    }


}
