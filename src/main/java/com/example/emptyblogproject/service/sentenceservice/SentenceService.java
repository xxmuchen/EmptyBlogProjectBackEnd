package com.example.emptyblogproject.service.sentenceservice;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.emptyblogproject.bean.dairy.Diary;
import com.example.emptyblogproject.bean.datavisualization.bo.DataVisualizationBO;
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

    public List<Sentence> getAllSentenceBySee();

    /*获取名人名言列表*/
    public List<Sentence> quotesByFamousPeopleBySee();

    public List<Sentence> getRecommendSentenceListBySee();

    /*分页查询用户个人空间所有日记*/
    public Page<Sentence> getUserSpaceSentenceOrderCreateTime(int currentPage , Long userId);

    /*获取用户点赞的日记  按点赞时间排序*/
    public Page<Sentence> getUserSpaceSentenceUserStarOrderByCreateTime(int currentPage , Long userId);

    public Page<Sentence> getUserSpaceSentenceUserCollectionOrderByCreateTime(int currentPage , Long userId);

    /*管理员获取所有句子*/
    public Page<Sentence> adminGetAllSentenceByPageAndCreateTime(int currentPage);

    public DataVisualizationBO getSentenceWriteAWeekDataVisualization();

    public DataVisualizationBO getSentenceStarAWeekDataVisualization();

    public DataVisualizationBO getSentenceCollectAWeekDataVisualization();

    public DataVisualizationBO getSentenceObserveAWeekDataVisualization();
}
