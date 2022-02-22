package com.example.emptyblogproject.service.commentsservice.commentsrootservice.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.emptyblogproject.bean.comments.commentsliked.CommentsLiked;
import com.example.emptyblogproject.bean.comments.commentsroot.CommentsRoot;
import com.example.emptyblogproject.mapper.commentsmapper.commentslikedmapper.CommentsLikedMapper;
import com.example.emptyblogproject.mapper.commentsmapper.commentsrootmapper.CommentsRootMapper;
import com.example.emptyblogproject.service.commentsservice.commentslikedservice.CommentsLikedService;
import com.example.emptyblogproject.service.commentsservice.commentsrootservice.CommentsRootService;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * User: 王程翔
 * Date: 2022/2/22
 * Time: 19:35
 * Description: 父评论记录serviceImpl
 */
@Service
public class CommentsRootServiceImpl extends ServiceImpl<CommentsRootMapper, CommentsRoot> implements CommentsRootService {
}
