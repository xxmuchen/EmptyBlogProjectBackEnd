package com.example.emptyblogproject.mapper.commentsmapper.commentslikedmapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.emptyblogproject.bean.comments.commentsliked.CommentsLiked;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created with IntelliJ IDEA.
 * User: 王程翔
 * Date: 2022/2/22
 * Time: 19:28
 * Description: 评论喜欢记录的mapper
 */
@Mapper
public interface CommentsLikedMapper extends BaseMapper<CommentsLiked> {

}
