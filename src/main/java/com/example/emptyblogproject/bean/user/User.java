package com.example.emptyblogproject.bean.user;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Data
public class User implements Serializable {
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;
    private String userName;
    private String password;
    private String sex;
    private String synopsis;
    private String email;
    private Date birthday;
    private String avatar;
    private String location;

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
    @TableLogic(value = "0" , delval = "1")
    private boolean del;

}
