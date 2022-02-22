package com.example.emptyblogproject.bean.productionstar;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class ProductionStar implements Serializable {
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;
    private Long userId;
    private Long objId;
    private String type;
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
    @TableLogic(value = "0", delval = "1")
    private boolean del;


}