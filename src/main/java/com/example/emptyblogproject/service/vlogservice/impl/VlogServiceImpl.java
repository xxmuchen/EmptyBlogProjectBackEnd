package com.example.emptyblogproject.service.vlogservice.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.emptyblogproject.bean.dairy.Diary;
import com.example.emptyblogproject.bean.sentence.Sentence;
import com.example.emptyblogproject.bean.vlog.Vlog;
import com.example.emptyblogproject.mapper.vlogmapper.VlogMapper;
import com.example.emptyblogproject.service.vlogservice.VlogService;
import org.springframework.stereotype.Service;

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
        List<Vlog> vlogList = this.list(queryWrapper);
        return vlogList;
    }

    @Override
    public Page<Vlog> getUserSpaceVlogOrderCreateTime(int currentPage, Long userId) {
        Page<Vlog> page = new Page<>(currentPage , 7);
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
}
