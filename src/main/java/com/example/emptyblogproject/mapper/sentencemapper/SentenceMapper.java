package com.example.emptyblogproject.mapper.sentencemapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.emptyblogproject.bean.dairy.Diary;
import com.example.emptyblogproject.bean.sentence.Sentence;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: 王程翔
 * Date: 2022/2/25
 * Time: 0:36
 * Description:
 */
@Mapper
public interface SentenceMapper extends BaseMapper<Sentence> {
    @Select("select sentence.id , sentence.content ,\n" +
            "sentence.original_author , sentence.bg_color,\n" +
            "sentence.author_id , sentence.author_name,\n" +
            "sentence.author_avatar , sentence.create_time,\n" +
            "sentence.update_time, sentence.see,sentence.sentence_id ,\n" +
            "sentence.del\n" +
            "from (select production_collection.obj_id , count(*) as count,del\n" +
            "from production_collection where del = 0 group by obj_id , del\n" +
            " order by count DESC ) as pc right join sentence on\n" +
            "     pc.obj_id = sentence.id and pc.del = 0 and\n" +
            "     sentence.del = 0 order by pc.count DESC  , sentence.create_time DESC")
    public List<Sentence> getRecommendSentenceList();
}
