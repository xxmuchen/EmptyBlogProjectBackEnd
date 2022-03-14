package com.example.emptyblogproject.bean.user;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: 王程翔
 * Date: 2022/3/15
 * Time: 2:09
 * Description:
 */
@Data
public class UserOperation {
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;
    private Long userId;
    private String userName;
    private String userIdentity;
    private String operation;
    private String type;
    private String content;
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
    @TableLogic(value = "0" , delval = "1")
    private boolean del;
}
