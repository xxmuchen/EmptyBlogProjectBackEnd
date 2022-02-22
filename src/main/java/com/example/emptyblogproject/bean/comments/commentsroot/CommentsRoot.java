package com.example.emptyblogproject.bean.comments.commentsroot;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: 王程翔
 * Date: 2022/2/22
 * Time: 17:58
 * Description: 父评论记录
 */
@Data
public class CommentsRoot {
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;
    private String type;
    private Long objId;
    private Long userId;
    private String userName;
    private String userAvatar;
    private String content;
    private int likeNum;
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
    @TableLogic(value = "0" , delval = "1")
    private boolean del;


}
