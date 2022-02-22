package com.example.emptyblogproject.service.commentsservice.commentsrootservice;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.emptyblogproject.bean.comments.commentsroot.CommentsRoot;
import com.example.emptyblogproject.bean.user.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: 王程翔
 * Date: 2022/2/22
 * Time: 19:30
 * Description: 父评论记录service
 */
public interface CommentsRootService extends IService<CommentsRoot> {

    /*找出该文章或资源下的所有评论*/
    public List<CommentsRoot> getALlCommentsByObjId(Long objId);

//    添加父评论
    public CommentsRoot addCommentsRoot(CommentsRoot commentsRoot , User user);

//    根据唯一标识获取父评论
    public CommentsRoot getCommentsRootByCommentsRootId(String commentsRootId);

    /*根据资源Id获取该资源的所有评论*/
    public List<CommentsRoot> getALlRootCommentsByObjId(Long objId);
}
