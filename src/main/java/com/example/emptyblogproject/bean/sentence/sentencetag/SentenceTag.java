package com.example.emptyblogproject.bean.sentence.sentencetag;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: 王程翔
 * Date: 2022/2/25
 * Time: 0:27
 * Description: 句子的标签
 */
@Data
public class SentenceTag {
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;
    private String tagName;
    private String sentenceId;

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
    @TableLogic(value = "0" , delval = "1")
    private boolean del;
}
