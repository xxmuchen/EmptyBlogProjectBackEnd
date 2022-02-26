package com.example.emptyblogproject.service.sentenceservice.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.emptyblogproject.bean.sentence.Sentence;
import com.example.emptyblogproject.mapper.sentencemapper.SentenceMapper;
import com.example.emptyblogproject.service.sentenceservice.SentenceService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: 王程翔
 * Date: 2022/2/25
 * Time: 0:39
 * Description:
 */
@Service
public class SentenceServiceImpl extends ServiceImpl<SentenceMapper , Sentence> implements SentenceService {

    @Override
    public List<Sentence> getAllSentence() {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("see" , true);
        List<Sentence> sentenceList = this.list(queryWrapper);
        return sentenceList;
    }

    @Override
    public List<Sentence> quotesByFamousPeople() {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("see" , true);
        queryWrapper.isNotNull("original_author");
        queryWrapper.ne("original_author" , "");
        List<Sentence> sentenceList = this.list(queryWrapper);
        return sentenceList;
    }

    @Override
    public List<Sentence> getRecommendSentenceList() {
        List<Sentence> sentenceList = baseMapper.getRecommendSentenceList();
        return sentenceList;
    }

}
