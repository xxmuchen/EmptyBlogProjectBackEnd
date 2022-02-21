package com.example.emptyblogproject.mapper.diarymapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.emptyblogproject.bean.dairy.Diary;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DiaryMapper extends BaseMapper<Diary> {

}
