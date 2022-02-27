package com.example.emptyblogproject.service.sentenceservice.sentencetagservice;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.emptyblogproject.bean.sentence.sentencetag.SentenceTag;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: 王程翔
 * Date: 2022/2/25
 * Time: 0:52
 * Description:
 */
public interface SentenceTagService extends IService<SentenceTag> {

    public List<SentenceTag> getTagsOrderByCount();

    public List<SentenceTag> getTagsBySentenceSentenceId(String SentenceSentenceId);

}
