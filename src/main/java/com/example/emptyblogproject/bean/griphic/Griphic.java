package com.example.emptyblogproject.bean.griphic;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: 王程翔
 * Date: 2022/3/4
 * Time: 10:09
 * Description:
 */
@Data
public class Griphic {
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;
    private String imageUrl;
    private String description;
    private String bgColor;
    private String type;
    private Long authorId;
    private String authorName;
    private String authorAvatar;
    private String errorReason;
    @TableField(fill = FieldFill.INSERT)
    private String state;
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
    private boolean see;
    @TableLogic(value = "0" , delval = "1")
    private boolean del;
}
