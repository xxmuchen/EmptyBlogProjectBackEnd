package com.example.emptyblogproject.bean.dairy;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import java.io.Serializable;
import java.util.Date;

@Data
//@Validated
public class Diary implements Serializable {
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;
    private String title;
    private String content;
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
    private String mood;
    private String weather;
    private String bgColor;
    private String musicUrl;
    private Long authorId;
    private String authorName;
    private boolean see;
    @TableField(updateStrategy = FieldStrategy.IGNORED)
    private String errorReason;
    @TableField(fill = FieldFill.INSERT)
    private String state;
    @TableLogic(value = "0" , delval = "1")
    private boolean del;


}
