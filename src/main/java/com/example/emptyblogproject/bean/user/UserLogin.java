package com.example.emptyblogproject.bean.user;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: 王程翔
 * Date: 2022/3/15
 * Time: 1:52
 * Description: 用户登录收集信息
 */

@Data
public class UserLogin {
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;
    private Long userId;
    private String userName;
    private String userIdentity;
    private String ipAddress;

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
    @TableLogic(value = "0" , delval = "1")
    private boolean del;

}
