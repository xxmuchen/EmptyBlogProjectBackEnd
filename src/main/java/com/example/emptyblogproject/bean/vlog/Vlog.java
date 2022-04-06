package com.example.emptyblogproject.bean.vlog;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: 王程翔
 * Date: 2022/2/27
 * Time: 14:49
 * Description:
 */
@Data
public class Vlog {

    @TableId(type = IdType.ASSIGN_ID)
    private Long id;
    private String title;
    private String videoUrl;
    private String description;
    private Long authorId;
    private String authorName;
    private String authorAvatar;
    private String vlogId;
    private boolean see;
    @TableField(updateStrategy = FieldStrategy.IGNORED)
    private String errorReason;
    @TableField(fill = FieldFill.INSERT)
    private String state;
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
    @TableLogic(value = "0" , delval = "1")
    private boolean del;
}
