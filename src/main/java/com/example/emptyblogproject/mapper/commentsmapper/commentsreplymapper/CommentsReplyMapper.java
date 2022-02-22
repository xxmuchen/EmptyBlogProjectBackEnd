package com.example.emptyblogproject.mapper.commentsmapper.commentsreplymapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.emptyblogproject.bean.comments.commentsreply.CommentsReply;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created with IntelliJ IDEA.
 * User: 王程翔
 * Date: 2022/2/22
 * Time: 19:32
 * Description: 子评论记录mapper
 */
@Mapper
@Deprecated
public interface CommentsReplyMapper extends BaseMapper<CommentsReply> {

}
