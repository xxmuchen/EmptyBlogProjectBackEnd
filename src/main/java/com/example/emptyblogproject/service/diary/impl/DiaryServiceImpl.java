package com.example.emptyblogproject.service.diary.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.emptyblogproject.bean.dairy.Diary;
import com.example.emptyblogproject.mapper.diarypagemapper.DiaryMapper;
import com.example.emptyblogproject.service.diary.DiaryService;
import org.springframework.stereotype.Service;

@Service
public class DiaryServiceImpl extends ServiceImpl<DiaryMapper , Diary> implements DiaryService {

}
