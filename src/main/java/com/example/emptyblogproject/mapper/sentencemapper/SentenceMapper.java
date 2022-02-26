package com.example.emptyblogproject.mapper.sentencemapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.emptyblogproject.bean.sentence.Sentence;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created with IntelliJ IDEA.
 * User: 王程翔
 * Date: 2022/2/25
 * Time: 0:36
 * Description:
 */
@Mapper
public interface SentenceMapper extends BaseMapper<Sentence> {
}
