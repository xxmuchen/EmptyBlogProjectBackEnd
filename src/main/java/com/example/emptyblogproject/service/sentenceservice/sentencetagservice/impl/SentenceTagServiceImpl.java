package com.example.emptyblogproject.service.sentenceservice.sentencetagservice.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.emptyblogproject.bean.sentence.sentencetag.SentenceTag;
import com.example.emptyblogproject.mapper.sentencemapper.sentencetagmapper.SentenceTagMapper;
import com.example.emptyblogproject.service.sentenceservice.sentencetagservice.SentenceTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: 王程翔
 * Date: 2022/2/25
 * Time: 0:52
 * Description:
 */
@Service
public class SentenceTagServiceImpl extends ServiceImpl<SentenceTagMapper , SentenceTag> implements SentenceTagService {

//    @Autowired
//    SentenceTagMapper sentenceTagMapper;

    @Override
    public List<SentenceTag> getTagsOrderByCount() {
        List<SentenceTag> sentenceTagList = baseMapper.getTagsOrderByCount();
        return sentenceTagList;
    }

    @Override
    public List<SentenceTag> getTagsBySentenceSentenceId(String SentenceSentenceId) {
        QueryWrapper<SentenceTag> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("sentence_id" , SentenceSentenceId);
        List<SentenceTag> sentenceTagList = this.list(queryWrapper);
        return sentenceTagList;
    }
}
