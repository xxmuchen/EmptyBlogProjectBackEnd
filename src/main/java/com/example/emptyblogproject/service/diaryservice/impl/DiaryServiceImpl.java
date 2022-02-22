package com.example.emptyblogproject.service.diaryservice.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.emptyblogproject.bean.dairy.Diary;
import com.example.emptyblogproject.mapper.diarymapper.DiaryMapper;
import com.example.emptyblogproject.service.diaryservice.DiaryService;
import org.springframework.stereotype.Service;

@Service
public class DiaryServiceImpl extends ServiceImpl<DiaryMapper , Diary> implements DiaryService {

}
