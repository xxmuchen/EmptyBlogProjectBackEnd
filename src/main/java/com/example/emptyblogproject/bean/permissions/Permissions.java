package com.example.emptyblogproject.bean.permissions;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: 王程翔
 * Date: 2022/3/13
 * Time: 18:19
 * Description: 权限
 */
@Data
public class Permissions {
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;
    private Long userId;
    private int userPermission;

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
    @TableLogic(value = "0" , delval = "1")
    private boolean del;
}
