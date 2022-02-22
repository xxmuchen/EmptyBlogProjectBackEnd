package com.example.emptyblogproject.bean.observe;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: 王程翔
 * Date: 2022/2/23
 * Time: 2:50
 * Description: 评论类
 */
@Data
@Entity
public class Observe {


    @TableId(type = IdType.ASSIGN_ID)
    private Long id;
    private String type;
    private Long objId;
    private Long observerId;
    private String observeContent;
    private Long lastId;
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
    @TableLogic(value = "0" , delval = "1")
    private boolean del;

    public void setId(Long id) {
        this.id = id;
    }

    @Id
    public Long getId() {
        return id;
    }
}
