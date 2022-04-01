package com.example.emptyblogproject.mapper.sentencemapper.sentencetagmapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.emptyblogproject.bean.sentence.sentencetag.SentenceTag;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: 王程翔
 * Date: 2022/2/25
 * Time: 0:51
 * Description:
 */
@Mapper
public interface SentenceTagMapper extends BaseMapper<SentenceTag> {

//    获取九个最热门的标签
    @Select("select count(*) as count , tag_name from sentence_tag , sentence where\n" +
            "                                                                       sentence.sentence_id = sentence_tag.sentence_id and\n" +
            "                                                                       sentence.state = '审批通过' and\n" +
            "                                                                       sentence.del = 0 and\n" +
            "                                                                       sentence_tag.del = 0 group by tag_name order by count DESC")
    public List<SentenceTag> getTagsOrderByCount();

    /*获取所有标签*/
    @Select("select tag_name from \n" +
            "                     sentence_tag , sentence where\n" +
            "                                                   sentence.sentence_id = sentence_tag.sentence_id and\n" +
            "                                                   sentence.state = '审批通过' and\n" +
            "                                                   sentence.del = 0 and\n" +
            "                                                   sentence_tag.del = 0\n" +
            "                                                   group by  tag_name")
    public List<SentenceTag> getAllTagsStateSuccess();
}
