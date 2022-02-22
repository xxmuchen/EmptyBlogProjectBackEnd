package com.example.emptyblogproject.service.commentsservice.commentsreplyservice.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.emptyblogproject.bean.comments.commentsliked.CommentsLiked;
import com.example.emptyblogproject.bean.comments.commentsreply.CommentsReply;
import com.example.emptyblogproject.bean.comments.commentsroot.CommentsRoot;
import com.example.emptyblogproject.bean.user.User;
import com.example.emptyblogproject.mapper.commentsmapper.commentslikedmapper.CommentsLikedMapper;
import com.example.emptyblogproject.mapper.commentsmapper.commentsreplymapper.CommentsReplyMapper;
import com.example.emptyblogproject.service.commentsservice.commentslikedservice.CommentsLikedService;
import com.example.emptyblogproject.service.commentsservice.commentsreplyservice.CommentsReplyService;
import com.example.emptyblogproject.service.commentsservice.commentsrootservice.CommentsRootService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 * User: 王程翔
 * Date: 2022/2/22
 * Time: 19:37
 * Description: 子评论记录serviceImpl
 */
@Service
public class CommentsReplyServiceImpl extends ServiceImpl<CommentsReplyMapper, CommentsReply> implements CommentsReplyService {

    @Autowired
    CommentsRootService commentsRootService;

    /*@Override
    public CommentsReply addCommentsReply(CommentsReply commentsReply, User user , User beReviewer) {

        CommentsRoot commentsRoot = commentsRootService.getCommentsRootByCommentsRootId(commentsReply.getCommentsParentId());

        commentsReply.setUserId(user.getId());
        commentsReply.setUserAvatar(user.getAvatar());
        commentsReply.setUserName(user.getUserName());

        commentsReply.setBeReviewedUserAvatar(beReviewer.getAvatar());
        commentsReply.setBeReviewedUserId(beReviewer.getId());
        commentsReply.setBeReviewedUserName(beReviewer.getUserName());
        commentsReply.setCommentsReplyId(UUID.randomUUID().toString().replace("-" , "") + System.currentTimeMillis());
        boolean flag = this.save(commentsReply);
        return commentsReply;
    }*/

    @Override
    public CommentsReply addCommentsReply(CommentsReply commentsReply, User user) {

        CommentsRoot commentsRoot = commentsRootService.getCommentsRootByCommentsRootId(commentsReply.getCommentsParentId());

        commentsReply.setUserId(user.getId());
        commentsReply.setUserAvatar(user.getAvatar());
        commentsReply.setUserName(user.getUserName());

        commentsReply.setBeReviewedUserAvatar(commentsRoot.getUserAvatar());
        commentsReply.setBeReviewedUserId(commentsRoot.getUserId());
        commentsReply.setBeReviewedUserName(commentsRoot.getUserName());
        commentsReply.setCommentsReplyId(UUID.randomUUID().toString().replace("-" , "") + System.currentTimeMillis());
        boolean flag = this.save(commentsReply);
        if (flag) {
            return commentsReply;
        }else {
            throw new RuntimeException("评论失败，请重试");
        }
    }
}
