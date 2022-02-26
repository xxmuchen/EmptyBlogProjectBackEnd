package com.example.emptyblogproject.bean.sentence.sentencetag;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
    private Long sentenceId;
    private Date createTime;
    private Date updateTime;
    private boolean del;
}
