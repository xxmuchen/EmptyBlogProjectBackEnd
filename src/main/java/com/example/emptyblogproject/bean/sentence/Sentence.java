package com.example.emptyblogproject.bean.sentence;

import com.baomidou.mybatisplus.annotation.*;
import com.example.emptyblogproject.bean.sentence.sentencetag.SentenceTag;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: 王程翔
 * Date: 2022/2/25
 * Time: 0:10
 * Description: 句子类
 */
@Data
public class Sentence {
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;
    private String content;
    private String originalAuthor;
    private String bgColor;
    private Long authorId;
    private String authorName;
    private String authorAvatar;
    private boolean see;
    @TableField(updateStrategy = FieldStrategy.IGNORED)
    private String errorReason;
    @TableField(fill = FieldFill.INSERT)
    private String state;
    @TableField(exist = false)
    private List<SentenceTag> sentenceTagList;

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    private String sentenceId;
    @TableLogic(value = "0" , delval = "1")
    private boolean del;

}
