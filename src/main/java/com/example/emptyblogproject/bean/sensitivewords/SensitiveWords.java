package com.example.emptyblogproject.bean.sensitivewords;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: 王程翔
 * Date: 2022/3/20
 * Time: 18:19
 * Description:
 */
@Data
public class SensitiveWords {
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;
    private String sensitiveWord;
    private Long authorId;
    private String authorName;

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
    @TableLogic(value = "0" , delval = "1")
    private boolean del;
}
