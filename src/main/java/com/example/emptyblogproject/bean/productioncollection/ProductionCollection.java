package com.example.emptyblogproject.bean.productioncollection;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.util.Date;

@Data
public class ProductionCollection {
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;
    private Long userId;
    private String type;
    private Long objId;

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
    @TableLogic(value = "0" , delval = "1")
    private boolean del;


}
