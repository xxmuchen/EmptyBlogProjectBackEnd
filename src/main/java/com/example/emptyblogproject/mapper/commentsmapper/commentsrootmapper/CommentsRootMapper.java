package com.example.emptyblogproject.mapper.commentsmapper.commentsrootmapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.emptyblogproject.bean.comments.commentsroot.CommentsRoot;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created with IntelliJ IDEA.
 * User: 王程翔
 * Date: 2022/2/22
 * Time: 19:30
 * Description: 父评论记录mapper
 */
@Mapper
public interface CommentsRootMapper extends BaseMapper<CommentsRoot> {

}
