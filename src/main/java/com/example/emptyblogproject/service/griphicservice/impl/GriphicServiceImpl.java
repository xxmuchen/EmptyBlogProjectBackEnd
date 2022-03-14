package com.example.emptyblogproject.service.griphicservice.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.emptyblogproject.bean.dairy.Diary;
import com.example.emptyblogproject.bean.griphic.Griphic;
import com.example.emptyblogproject.mapper.griphicmapper.GriphicMapper;
import com.example.emptyblogproject.service.griphicservice.GriphicService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: 王程翔
 * Date: 2022/3/4
 * Time: 10:13
 * Description:
 */
@Service
public class GriphicServiceImpl extends ServiceImpl<GriphicMapper , Griphic> implements GriphicService {

    @Override
    public List<Griphic> getAllGriphicBySee() {
        QueryWrapper<Griphic> queryWrapper = new QueryWrapper();
        queryWrapper.eq("see" , true);
        List<Griphic> griphicList = this.list(queryWrapper);
        return griphicList;
    }

    @Override
    public List<Griphic> getBuiltifulImageAndSentence() {
        QueryWrapper<Griphic> queryWrapper = new QueryWrapper();
        queryWrapper.eq("see" , true);
        queryWrapper.eq("type" , "美图美句");
        List<Griphic> griphicList = this.list(queryWrapper);
        return griphicList;
    }

    @Override
    public List<Griphic> getHandWriteBeautifulSentence() {
        QueryWrapper<Griphic> queryWrapper = new QueryWrapper();
        queryWrapper.eq("see" , true);
        queryWrapper.eq("type" , "手写美句");
        List<Griphic> griphicList = this.list(queryWrapper);
        return griphicList;
    }

    @Override
    public List<Griphic> getClassicDialogue() {
        QueryWrapper<Griphic> queryWrapper = new QueryWrapper();
        queryWrapper.eq("see" , true);
        queryWrapper.eq("type" , "经典对白");
        List<Griphic> griphicList = this.list(queryWrapper);
        return griphicList;
    }

    @Override
    public List<Griphic> getUserSpaceGriphicOrderCreateTime(Long userId) {
//        Page<Griphic> page = new Page<>(currentPage , 7);
        QueryWrapper<Griphic> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("author_id" , userId);
        queryWrapper.orderByDesc("create_time");
//        Page<Griphic> diaryPage = this.page(page, queryWrapper);
        List<Griphic> griphicList = this.list(queryWrapper);
        return griphicList;
    }

    @Override
    public List<Griphic> getUserSpaceGriphicUserStarOrderByCreateTime(Long userId) {
//        Page<Griphic> page = new Page<>(currentPage , 7);
        List<Griphic> userSpaceGriphicUserStarOrderByCreateTime = baseMapper.getUserSpaceGriphicUserStarOrderByCreateTime(userId);
        return userSpaceGriphicUserStarOrderByCreateTime;
    }

    @Override
    public List<Griphic> getUserSpaceGriphicUserCollectionOrderByCreateTime(Long userId) {
        List<Griphic> userSpaceGriphicUserCollectionOrderByCreateTime = baseMapper.getUserSpaceGriphicUserCollectionOrderByCreateTime(userId);
        return userSpaceGriphicUserCollectionOrderByCreateTime;
    }

    @Override
    public List<Griphic> adminGetAllGriphicByPageAndCreateTime() {
        QueryWrapper<Griphic> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("create_time");
        List<Griphic> griphicList = this.list(queryWrapper);
        return griphicList;
    }
}
