package com.example.emptyblogproject.service.vlogservice.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.emptyblogproject.bean.dairy.Diary;
import com.example.emptyblogproject.bean.datavisualization.DataVisualizationDateAndInteger;
import com.example.emptyblogproject.bean.datavisualization.bo.DataVisualizationBO;
import com.example.emptyblogproject.bean.sentence.Sentence;
import com.example.emptyblogproject.bean.vlog.Vlog;
import com.example.emptyblogproject.mapper.vlogmapper.VlogMapper;
import com.example.emptyblogproject.service.vlogservice.VlogService;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: 王程翔
 * Date: 2022/2/27
 * Time: 14:57
 * Description:
 */

@Service
public class VlogServiceImpl extends ServiceImpl<VlogMapper , Vlog> implements VlogService {
    @Override
    public List<Vlog> getAllVlogBySee() {
        QueryWrapper<Vlog> queryWrapper = new QueryWrapper();
        queryWrapper.eq("see" , 1);
        queryWrapper.eq("state" , "审批成功");
        List<Vlog> vlogList = this.list(queryWrapper);
        return vlogList;
    }

    @Override
    public Page<Vlog> getUserSpaceVlogOrderCreateTime(int currentPage, Long userId) {
        Page<Vlog> page = new Page<>(currentPage , 6);
        QueryWrapper<Vlog> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("author_id" , userId);
        queryWrapper.orderByDesc("create_time");
        Page<Vlog> vlogPage = this.page(page, queryWrapper);
        return vlogPage;
    }

    @Override
    public Page<Vlog> getUserSpaceVlogUserStarOrderByCreateTime(int currentPage , Long userId) {
        Page<Vlog> page = new Page<>(currentPage , 7);
        Page<Vlog> userSpaceVlogUserStarOrderByCreateTime = baseMapper.getUserSpaceVlogUserStarOrderByCreateTime(page, userId);
        return userSpaceVlogUserStarOrderByCreateTime;
    }

    @Override
    public Page<Vlog> getUserSpaceVlogUserCollectionOrderByCreateTime(int currentPage, Long userId) {
        Page<Vlog> page = new Page<>(currentPage , 7);
        Page<Vlog> userSpaceVlogUserCollectionOrderByCreateTime = baseMapper.getUserSpaceVlogUserCollectionOrderByCreateTime(page, userId);
        return userSpaceVlogUserCollectionOrderByCreateTime;
    }

    @Override
    public Page<Vlog> adminGetAllVlogByPageAndCreateTime(int currentPage) {
        Page<Vlog> page = new Page<>(currentPage , 6);
        QueryWrapper<Vlog> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("create_time");
        Page<Vlog> vlogPage = this.page(page, queryWrapper);
        return vlogPage;
    }

    @Override
    public DataVisualizationBO getVlogWriteAWeekDataVisualization() {
        List<DataVisualizationDateAndInteger> vlogWriteAWeekDataVisualization = baseMapper.getVlogWriteAWeekDataVisualization();
        System.out.println(vlogWriteAWeekDataVisualization);
        DataVisualizationBO dataVisualizationBO = new DataVisualizationBO();
        String strDateFormat = "yyyy-MM-dd";
        DateFormat dateFormat = new SimpleDateFormat(strDateFormat);
        for (DataVisualizationDateAndInteger dataVisualizationDateAndInteger: vlogWriteAWeekDataVisualization) {
            String format = dateFormat.format(dataVisualizationDateAndInteger.getTime());
            dataVisualizationBO.getXAxis().add(format);
            dataVisualizationBO.getYAxis().add(dataVisualizationDateAndInteger.getNumber());
        }
        return dataVisualizationBO;
    }

    @Override
    public DataVisualizationBO getVlogStarAWeekDataVisualization() {
        List<DataVisualizationDateAndInteger> vlogStarAWeekDataVisualization = baseMapper.getVlogStarAWeekDataVisualization();
        System.out.println(vlogStarAWeekDataVisualization);
        DataVisualizationBO dataVisualizationBO = new DataVisualizationBO();
        String strDateFormat = "yyyy-MM-dd";
        DateFormat dateFormat = new SimpleDateFormat(strDateFormat);
        for (DataVisualizationDateAndInteger dataVisualizationDateAndInteger: vlogStarAWeekDataVisualization) {
            String format = dateFormat.format(dataVisualizationDateAndInteger.getTime());
            dataVisualizationBO.getXAxis().add(format);
            dataVisualizationBO.getYAxis().add(dataVisualizationDateAndInteger.getNumber());
        }
        return dataVisualizationBO;
    }

    @Override
    public DataVisualizationBO getVlogCollectAWeekDataVisualization() {
        List<DataVisualizationDateAndInteger> vlogCollectAWeekDataVisualization = baseMapper.getVlogCollectAWeekDataVisualization();
        System.out.println(vlogCollectAWeekDataVisualization);
        DataVisualizationBO dataVisualizationBO = new DataVisualizationBO();
        String strDateFormat = "yyyy-MM-dd";
        DateFormat dateFormat = new SimpleDateFormat(strDateFormat);
        for (DataVisualizationDateAndInteger dataVisualizationDateAndInteger: vlogCollectAWeekDataVisualization) {
            String format = dateFormat.format(dataVisualizationDateAndInteger.getTime());
            dataVisualizationBO.getXAxis().add(format);
            dataVisualizationBO.getYAxis().add(dataVisualizationDateAndInteger.getNumber());
        }
        return dataVisualizationBO;
    }

    @Override
    public DataVisualizationBO getVlogObserveAWeekDataVisualization() {
        List<DataVisualizationDateAndInteger> vlogObserveAWeekDataVisualization = baseMapper.getVlogObserveAWeekDataVisualization();
        System.out.println(vlogObserveAWeekDataVisualization);
        DataVisualizationBO dataVisualizationBO = new DataVisualizationBO();
        String strDateFormat = "yyyy-MM-dd";
        DateFormat dateFormat = new SimpleDateFormat(strDateFormat);
        for (DataVisualizationDateAndInteger dataVisualizationDateAndInteger: vlogObserveAWeekDataVisualization) {
            String format = dateFormat.format(dataVisualizationDateAndInteger.getTime());
            dataVisualizationBO.getXAxis().add(format);
            dataVisualizationBO.getYAxis().add(dataVisualizationDateAndInteger.getNumber());
        }
        return dataVisualizationBO;
    }

    @Override
    public Page<Vlog> getUserSpaceVlogStateFailOrderCreateTime(int currentPage, Long userId) {
        Page<Vlog> page = new Page<>(currentPage , 6);
        QueryWrapper<Vlog> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("author_id" , userId);
        queryWrapper.eq("state" , "审批不通过");
        queryWrapper.orderByDesc("create_time");
        Page<Vlog> vlogPage = this.page(page, queryWrapper);
        return vlogPage;
    }

    @Override
    public Page<Vlog> getUserSpaceVlogStateWaitOrderCreateTime(int currentPage, Long userId) {
        Page<Vlog> page = new Page<>(currentPage , 6);
        QueryWrapper<Vlog> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("state" , "待审批");
        queryWrapper.eq("author_id" , userId);
        queryWrapper.orderByDesc("create_time");
        Page<Vlog> vlogPage = this.page(page, queryWrapper);
        return vlogPage;
    }

    @Override
    public Page<Vlog> getUserSpaceVlogStateSuccessOrderCreateTime(int currentPage, Long userId) {
        Page<Vlog> page = new Page<>(currentPage , 6);
        QueryWrapper<Vlog> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("author_id" , userId);
        queryWrapper.eq("state" , "审批通过");
        queryWrapper.orderByDesc("create_time");
        Page<Vlog> vlogPage = this.page(page, queryWrapper);
        return vlogPage;
    }
}
