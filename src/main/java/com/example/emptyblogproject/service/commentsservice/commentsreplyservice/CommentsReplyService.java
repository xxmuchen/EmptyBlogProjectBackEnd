package com.example.emptyblogproject.service.commentsservice.commentsreplyservice;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.emptyblogproject.bean.comments.commentsreply.CommentsReply;
import com.example.emptyblogproject.bean.comments.commentsroot.CommentsRoot;
import com.example.emptyblogproject.bean.user.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created with IntelliJ IDEA.
 * User: 王程翔
 * Date: 2022/2/22
 * Time: 19:32
 * Description: 子评论记录service
 */
@Deprecated
public interface CommentsReplyService extends IService<CommentsReply> {

//    public CommentsReply addCommentsReply(CommentsReply commentsReply , User userservice , User beReviewer);
    /*添加子评论*/
    public CommentsReply addCommentsReply(CommentsReply commentsReply , User user);


}
