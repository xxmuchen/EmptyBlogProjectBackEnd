package com.example.emptyblogproject.bean.admin;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: 王程翔
 * Date: 2022/3/13
 * Time: 12:47
 * Description:
 */
@Data
@Deprecated
public class Admin {
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;
    private String adminName;
    private String adminPhone;
    private String adminPassword;
    private String adminAvatar;

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
    @TableLogic(value = "0" , delval = "1")
    private boolean del;

}
