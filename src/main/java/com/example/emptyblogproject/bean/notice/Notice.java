package com.example.emptyblogproject.bean.notice;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: 王程翔
 * Date: 2022/4/10
 * Time: 8:25
 * Description:
 */
@Data
public class Notice {
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;
    private String content;
    private Long authorId;
    private String authorName;
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
    @TableLogic(value = "0" , delval = "1")
    private boolean del;
}
