package com.example.emptyblogproject.service.sentenceservice.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.emptyblogproject.bean.sentence.Sentence;
import com.example.emptyblogproject.mapper.sentencemapper.SentenceMapper;
import com.example.emptyblogproject.service.sentenceservice.SentenceService;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * User: 王程翔
 * Date: 2022/2/25
 * Time: 0:39
 * Description:
 */
@Service
public class SentenceServiceImpl extends ServiceImpl<SentenceMapper , Sentence> implements SentenceService {

}
