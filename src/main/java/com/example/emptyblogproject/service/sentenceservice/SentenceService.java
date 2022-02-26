package com.example.emptyblogproject.service.sentenceservice;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.emptyblogproject.bean.sentence.Sentence;
import com.example.emptyblogproject.mapper.sentencemapper.SentenceMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: 王程翔
 * Date: 2022/2/25
 * Time: 0:36
 * Description:
 */

public interface SentenceService extends IService<Sentence> {

    public List<Sentence> getAllSentence();

    /*获取名人名言列表*/
    public List<Sentence> quotesByFamousPeople();

    public List<Sentence> getRecommendSentenceList();



}
