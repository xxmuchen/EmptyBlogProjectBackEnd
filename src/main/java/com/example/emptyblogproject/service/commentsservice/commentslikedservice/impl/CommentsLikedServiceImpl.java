package com.example.emptyblogproject.service.commentsservice.commentslikedservice.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.emptyblogproject.bean.comments.commentsliked.CommentsLiked;
import com.example.emptyblogproject.mapper.commentsmapper.commentslikedmapper.CommentsLikedMapper;
import com.example.emptyblogproject.service.commentsservice.commentslikedservice.CommentsLikedService;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * User: 王程翔
 * Date: 2022/2/22
 * Time: 19:37
 * Description: 评论喜欢记录serviceImpl
 */
@Service
@Deprecated
public class CommentsLikedServiceImpl extends ServiceImpl<CommentsLikedMapper , CommentsLiked> implements CommentsLikedService {
}
