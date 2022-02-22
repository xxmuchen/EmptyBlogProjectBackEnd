package com.example.emptyblogproject.service.diaryservice.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.emptyblogproject.bean.dairy.Diary;
import com.example.emptyblogproject.mapper.diarymapper.DiaryMapper;
import com.example.emptyblogproject.service.diaryservice.DiaryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiaryServiceImpl extends ServiceImpl<DiaryMapper , Diary> implements DiaryService {


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


}
