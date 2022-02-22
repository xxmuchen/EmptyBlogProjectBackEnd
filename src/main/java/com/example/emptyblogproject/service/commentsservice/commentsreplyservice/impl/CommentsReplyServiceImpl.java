package com.example.emptyblogproject.service.commentsservice.commentsreplyservice.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.emptyblogproject.bean.comments.commentsliked.CommentsLiked;
import com.example.emptyblogproject.bean.comments.commentsreply.CommentsReply;
import com.example.emptyblogproject.mapper.commentsmapper.commentslikedmapper.CommentsLikedMapper;
import com.example.emptyblogproject.mapper.commentsmapper.commentsreplymapper.CommentsReplyMapper;
import com.example.emptyblogproject.service.commentsservice.commentslikedservice.CommentsLikedService;
import com.example.emptyblogproject.service.commentsservice.commentsreplyservice.CommentsReplyService;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * User: 王程翔
 * Date: 2022/2/22
 * Time: 19:37
 * Description: 子评论记录serviceImpl
 */
@Service
public class CommentsReplyServiceImpl extends ServiceImpl<CommentsReplyMapper, CommentsReply> implements CommentsReplyService {
}
