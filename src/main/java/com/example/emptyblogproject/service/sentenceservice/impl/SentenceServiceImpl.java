package com.example.emptyblogproject.service.sentenceservice.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.emptyblogproject.bean.dairy.Diary;
import com.example.emptyblogproject.bean.datavisualization.DataVisualizationDateAndInteger;
import com.example.emptyblogproject.bean.datavisualization.bo.DataVisualizationBO;
import com.example.emptyblogproject.bean.sentence.Sentence;
import com.example.emptyblogproject.bean.sentence.sentencetag.SentenceTag;
import com.example.emptyblogproject.mapper.sentencemapper.SentenceMapper;
import com.example.emptyblogproject.service.sentenceservice.SentenceService;
import com.example.emptyblogproject.service.sentenceservice.sentencetagservice.SentenceTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

    @Autowired
    SentenceTagService sentenceTagService;

    @Override
    public List<Sentence> getAllSentenceBySee() {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("see" , true);
        queryWrapper.eq("state" , "审批通过");
        List<Sentence> sentenceList = this.list(queryWrapper);

        this.addTagsToSentenceList(sentenceList);

        return sentenceList;
    }

    @Override
    public List<Sentence> quotesByFamousPeopleBySee() {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("see" , true);

        queryWrapper.eq("state" , "审批通过");
        queryWrapper.isNotNull("original_author");

        queryWrapper.ne("original_author" , "");
        List<Sentence> sentenceList = this.list(queryWrapper);

        this.addTagsToSentenceList(sentenceList);


        return sentenceList;
    }

    @Override
    public List<Sentence> getRecommendSentenceListBySee() {
        List<Sentence> sentenceList = baseMapper.getRecommendSentenceList();

        this.addTagsToSentenceList(sentenceList);

        return sentenceList;
    }

    @Override
    public Page<Sentence> getUserSpaceSentenceOrderCreateTime(int currentPage, Long userId) {
        Page<Sentence> page = new Page<>(currentPage , 6);
        QueryWrapper<Sentence> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("author_id" , userId);
//        queryWrapper.eq("state" , "审批通过");
        queryWrapper.orderByDesc("create_time");
        Page<Sentence> sentencePage = this.page(page, queryWrapper);
        this.addTagsToSentencePage(sentencePage);
        return sentencePage;
    }

    @Override
    public Page<Sentence> getUserSpaceSentenceUserStarOrderByCreateTime(int currentPage, Long userId) {
        Page<Sentence> page = new Page<>(currentPage , 7);
        Page<Sentence> userSpaceSentenceUserStarOrderByCreateTime = baseMapper.getUserSpaceSentenceUserStarOrderByCreateTime(page, userId);
        this.addTagsToSentencePage(userSpaceSentenceUserStarOrderByCreateTime);
        return userSpaceSentenceUserStarOrderByCreateTime;
    }

    @Override
    public Page<Sentence> getUserSpaceSentenceUserCollectionOrderByCreateTime(int currentPage, Long userId) {
        Page<Sentence> page = new Page<>(currentPage , 7);
        Page<Sentence> userSpaceSentenceUserCollectionOrderByCreateTime = baseMapper.getUserSpaceSentenceUserCollectionOrderByCreateTime(page, userId);
        this.addTagsToSentencePage(userSpaceSentenceUserCollectionOrderByCreateTime);
        return userSpaceSentenceUserCollectionOrderByCreateTime;
    }

    @Override
    public Page<Sentence> adminGetAllSentenceByPageAndCreateTime(int currentPage) {
        Page<Sentence> page = new Page<>(currentPage , 6);
        QueryWrapper<Sentence> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("state");
        queryWrapper.orderByDesc("create_time");
        Page<Sentence> sentencePage = this.page(page, queryWrapper);
        this.addTagsToSentencePage(sentencePage);
        return sentencePage;
    }

    @Override
    public DataVisualizationBO getSentenceWriteAWeekDataVisualization() {
        List<DataVisualizationDateAndInteger> sentenceWriteAWeekDataVisualization = baseMapper.getSentenceWriteAWeekDataVisualization();
        System.out.println(sentenceWriteAWeekDataVisualization);
        DataVisualizationBO dataVisualizationBO = new DataVisualizationBO();
        String strDateFormat = "yyyy-MM-dd";
        DateFormat dateFormat = new SimpleDateFormat(strDateFormat);
        for (DataVisualizationDateAndInteger dataVisualizationDateAndInteger: sentenceWriteAWeekDataVisualization) {
            String format = dateFormat.format(dataVisualizationDateAndInteger.getTime());
            dataVisualizationBO.getXAxis().add(format);
            dataVisualizationBO.getYAxis().add(dataVisualizationDateAndInteger.getNumber());
        }
        return dataVisualizationBO;
    }

    @Override
    public DataVisualizationBO getSentenceStarAWeekDataVisualization() {
        List<DataVisualizationDateAndInteger> sentenceStarAWeekDataVisualization = baseMapper.getSentenceStarAWeekDataVisualization();
        System.out.println(sentenceStarAWeekDataVisualization);
        DataVisualizationBO dataVisualizationBO = new DataVisualizationBO();
        String strDateFormat = "yyyy-MM-dd";
        DateFormat dateFormat = new SimpleDateFormat(strDateFormat);
        for (DataVisualizationDateAndInteger dataVisualizationDateAndInteger: sentenceStarAWeekDataVisualization) {
            String format = dateFormat.format(dataVisualizationDateAndInteger.getTime());
            dataVisualizationBO.getXAxis().add(format);
            dataVisualizationBO.getYAxis().add(dataVisualizationDateAndInteger.getNumber());
        }
        return dataVisualizationBO;
    }

    @Override
    public DataVisualizationBO getSentenceCollectAWeekDataVisualization() {
        List<DataVisualizationDateAndInteger> sentenceCollectAWeekDataVisualization = baseMapper.getSentenceCollectAWeekDataVisualization();
        System.out.println(sentenceCollectAWeekDataVisualization);
        DataVisualizationBO dataVisualizationBO = new DataVisualizationBO();
        String strDateFormat = "yyyy-MM-dd";
        DateFormat dateFormat = new SimpleDateFormat(strDateFormat);
        for (DataVisualizationDateAndInteger dataVisualizationDateAndInteger: sentenceCollectAWeekDataVisualization) {
            String format = dateFormat.format(dataVisualizationDateAndInteger.getTime());
            dataVisualizationBO.getXAxis().add(format);
            dataVisualizationBO.getYAxis().add(dataVisualizationDateAndInteger.getNumber());
        }
        return dataVisualizationBO;
    }

    @Override
    public DataVisualizationBO getSentenceObserveAWeekDataVisualization() {
        List<DataVisualizationDateAndInteger> sentenceObserveAWeekDataVisualization = baseMapper.getSentenceObserveAWeekDataVisualization();
        System.out.println(sentenceObserveAWeekDataVisualization);
        DataVisualizationBO dataVisualizationBO = new DataVisualizationBO();
        String strDateFormat = "yyyy-MM-dd";
        DateFormat dateFormat = new SimpleDateFormat(strDateFormat);
        for (DataVisualizationDateAndInteger dataVisualizationDateAndInteger: sentenceObserveAWeekDataVisualization) {
            String format = dateFormat.format(dataVisualizationDateAndInteger.getTime());
            dataVisualizationBO.getXAxis().add(format);
            dataVisualizationBO.getYAxis().add(dataVisualizationDateAndInteger.getNumber());
        }
        return dataVisualizationBO;
    }

    @Override
    public Page<Sentence> getUserSpaceSentenceStateSuccessOrderCreateTime(int currentPage, Long userId) {
        Page<Sentence> page = new Page<>(currentPage , 6);
        QueryWrapper<Sentence> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("author_id" , userId);
        queryWrapper.eq("state" , "审批通过");
        queryWrapper.orderByDesc("create_time");
        Page<Sentence> sentencePage = this.page(page, queryWrapper);
        this.addTagsToSentencePage(sentencePage);
        return sentencePage;
    }

    @Override
    public Page<Sentence> getUserSpaceSentenceStateWaitOrderCreateTime(int currentPage, Long userId) {
        Page<Sentence> page = new Page<>(currentPage , 6);
        QueryWrapper<Sentence> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("author_id" , userId);
        queryWrapper.eq("state" , "待审批");
        queryWrapper.orderByDesc("create_time");
        Page<Sentence> sentencePage = this.page(page, queryWrapper);
        this.addTagsToSentencePage(sentencePage);
        return sentencePage;
    }

    @Override
    public Page<Sentence> getUserSpaceSentenceStateFailOrderCreateTime(int currentPage, Long userId) {
        Page<Sentence> page = new Page<>(currentPage , 6);
        QueryWrapper<Sentence> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("author_id" , userId);
        queryWrapper.eq("state" , "审批不通过");
        queryWrapper.orderByDesc("create_time");
        Page<Sentence> sentencePage = this.page(page, queryWrapper);
        this.addTagsToSentencePage(sentencePage);
        return sentencePage;
    }

    @Override
    public List<Sentence> getAllSentenceByTag(String tag) {
        QueryWrapper<Sentence> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("state" , "审批通过");
        queryWrapper.eq("see" , true);
        List<Sentence> sentences = this.list(queryWrapper);
        List<Sentence> sentenceList = new ArrayList<>();
        this.addTagsToSentenceList(sentences);
        for (Sentence sentence : sentences) {
            List<SentenceTag> sentenceTagList = sentence.getSentenceTagList();
            for (SentenceTag sentenceTag : sentenceTagList) {
                if (sentenceTag.getTagName().equals(tag)) {
                    sentenceList.add(sentence);
                    break;
                }
            }
        }
        return sentenceList;
    }


    /*为句子列表添加标签*/
    public void addTagsToSentenceList(List<Sentence> sentenceList) {
        for (Sentence sentence: sentenceList) {
            List<SentenceTag> sentenceTagList = sentenceTagService.getTagsBySentenceSentenceId(sentence.getSentenceId());
            sentence.setSentenceTagList(sentenceTagList);
        }
    }

    /*为句子列表添加标签*/
    public void addTagsToSentencePage(Page<Sentence> sentencePage) {
        List<Sentence> records = sentencePage.getRecords();
        this.addTagsToSentenceList(records);
        sentencePage.setRecords(records);
    }


}
