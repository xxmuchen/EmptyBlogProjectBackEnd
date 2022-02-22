package com.example.emptyblogproject.service.commentsservice.commentsrootservice.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.emptyblogproject.bean.comments.commentsliked.CommentsLiked;
import com.example.emptyblogproject.bean.comments.commentsroot.CommentsRoot;
import com.example.emptyblogproject.bean.user.User;
import com.example.emptyblogproject.mapper.commentsmapper.commentslikedmapper.CommentsLikedMapper;
import com.example.emptyblogproject.mapper.commentsmapper.commentsrootmapper.CommentsRootMapper;
import com.example.emptyblogproject.service.commentsservice.commentslikedservice.CommentsLikedService;
import com.example.emptyblogproject.service.commentsservice.commentsrootservice.CommentsRootService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 * User: 王程翔
 * Date: 2022/2/22
 * Time: 19:35
 * Description: 父评论记录serviceImpl
 */
@Service
public class CommentsRootServiceImpl extends ServiceImpl<CommentsRootMapper, CommentsRoot> implements CommentsRootService {

    @Override
    public List<CommentsRoot> getALlCommentsByObjId(Long objId) {
        QueryWrapper<CommentsRoot> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("obj_id" , objId);
        List<CommentsRoot> commentsRootList = this.list(queryWrapper);
        return commentsRootList;
    }

    @Override
    public CommentsRoot addCommentsRoot(CommentsRoot commentsRoot, User user) {
        commentsRoot.setUserId(user.getId());
        commentsRoot.setUserName(user.getUserName());
        commentsRoot.setUserAvatar(user.getAvatar());
        commentsRoot.setCommentRootId(UUID.randomUUID().toString().replace("-" , "") + System.currentTimeMillis());
        boolean flag = this.save(commentsRoot);
        if (flag) {
            return commentsRoot;
        }else {
            throw new RuntimeException("评论失败，请重试");
        }
    }

    @Override
    public CommentsRoot getCommentsRootByCommentsRootId(String commentsRootId) {
        QueryWrapper<CommentsRoot> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("comment_root_id" , commentsRootId);
        CommentsRoot commentsRoot = this.getOne(queryWrapper);

        return commentsRoot;
    }

    @Override
    public List<CommentsRoot> getALlRootCommentsByObjId(Long objId) {
        QueryWrapper<CommentsRoot> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("obj_id" , objId);
        List<CommentsRoot> commentsRootList = this.list(queryWrapper);
        if (commentsRootList != null) {
            return commentsRootList;
        }else {
            throw new RuntimeException("请刷新重试");
        }
    }
}
