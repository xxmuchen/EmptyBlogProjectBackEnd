package com.example.emptyblogproject.service.diary.diarystar.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.emptyblogproject.bean.dairy.diarystar.DiaryStar;
import com.example.emptyblogproject.mapper.diarymapper.diarystarmapper.DiaryStarMapper;
import com.example.emptyblogproject.service.diary.diarystar.DiaryStarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DiaryStarImpl extends ServiceImpl<DiaryStarMapper , DiaryStar> implements DiaryStarService {

    @Autowired
    DiaryStarMapper diaryStarMapper;

//    @Override
//    public boolean cancelDiaryStar(Long userId, Long diaryId) {
//        boolean flag = diaryStarMapper.cancelDiaryStar(userId, diaryId);
//        return flag;
//    }

    @Override
    public boolean reLikeDiary(Long id) {
        boolean flag = diaryStarMapper.reLikeDiary(id);
        return flag;
    }
}
