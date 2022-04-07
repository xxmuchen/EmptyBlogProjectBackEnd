package com.example.emptyblogproject.mapper.sentencemapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.emptyblogproject.bean.dairy.Diary;
import com.example.emptyblogproject.bean.datavisualization.DataVisualizationDateAndInteger;
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
            "     sentence.del = 0 and see = 1 and state = '审批成功' order by pc.count DESC  , sentence.create_time DESC")
    public List<Sentence> getRecommendSentenceList();



    @Select("select sentence.* from user , sentence , production_star where user.id = production_star.user_id\n" +
            "                and production_star.obj_id = sentence.id and user.id = #{userId} \n" +
            "                and user.del = false and sentence.del = false and production_star.del = false\n" +
            "                order by production_star.create_time DESC")
    public Page<Sentence> getUserSpaceSentenceUserStarOrderByCreateTime(Page<Sentence> pagination , Long userId);

    @Select("select sentence.* from user , sentence , production_collection where user.id = production_collection.user_id\n" +
            "                and production_collection.obj_id = sentence.id and user.id = #{userId} \n" +
            "                and user.del = false and sentence.del = false and production_collection.del = false\n" +
            "                order by production_collection.create_time DESC")
    public Page<Sentence> getUserSpaceSentenceUserCollectionOrderByCreateTime(Page<Sentence> pagination , Long userId);



    @Select("SELECT\n" +
            "        t1.timeDay as time,t2.number\n" +
            "    FROM (\n" +
            "        SELECT\n" +
            "            date_format(lastWeek.`timeDay`,'%Y-%m-%d') as 'timeDay'\n" +
            "        FROM (\n" +
            "            select DATE_SUB(NOW(),interval 7 day) as 'timeDay'\n" +
            "            UNION ALL\n" +
            "            select DATE_SUB(NOW(),interval 6 day) as 'timeDay'\n" +
            "            UNION ALL\n" +
            "            select DATE_SUB(NOW(),interval 5 day) as 'timeDay'\n" +
            "            UNION ALL\n" +
            "            select DATE_SUB(NOW(),interval 4 day) as 'timeDay'\n" +
            "            UNION ALL\n" +
            "            select DATE_SUB(NOW(),interval 3 day) as 'timeDay'\n" +
            "            UNION ALL\n" +
            "            select DATE_SUB(NOW(),interval 2 day) as 'timeDay'\n" +
            "            UNION ALL\n" +
            "            select DATE_SUB(NOW(),interval 1 day) as 'timeDay'\n" +
            "            UNION ALL\n" +
            "            select NOW() as 'timeDay'\n" +
            "        ) as lastWeek\n" +
            "    ) as t1\n" +
            "    LEFT JOIN (\n" +
            "        SELECT\n" +
            "            DATE_FORMAT(sentence.create_time,'%Y-%m-%d') time ,COUNT(sentence.id) as number\n" +
            "        FROM sentence where sentence.del = 0\n" +
            "        GROUP BY DATE_FORMAT(create_time,'%Y-%m-%d')\n" +
            "    ) as t2 ON t1.timeDay = t2.time\n" +
            "    ORDER BY t1.timeDay")
    public List<DataVisualizationDateAndInteger> getSentenceWriteAWeekDataVisualization();

    @Select("SELECT\n" +
            "        t1.timeDay as time,t2.number\n" +
            "    FROM (\n" +
            "        SELECT\n" +
            "            date_format(lastWeek.`timeDay`,'%Y-%m-%d') as 'timeDay'\n" +
            "        FROM (\n" +
            "            select DATE_SUB(NOW(),interval 7 day) as 'timeDay'\n" +
            "            UNION ALL\n" +
            "            select DATE_SUB(NOW(),interval 6 day) as 'timeDay'\n" +
            "            UNION ALL\n" +
            "            select DATE_SUB(NOW(),interval 5 day) as 'timeDay'\n" +
            "            UNION ALL\n" +
            "            select DATE_SUB(NOW(),interval 4 day) as 'timeDay'\n" +
            "            UNION ALL\n" +
            "            select DATE_SUB(NOW(),interval 3 day) as 'timeDay'\n" +
            "            UNION ALL\n" +
            "            select DATE_SUB(NOW(),interval 2 day) as 'timeDay'\n" +
            "            UNION ALL\n" +
            "            select DATE_SUB(NOW(),interval 1 day) as 'timeDay'\n" +
            "            UNION ALL\n" +
            "            select NOW() as 'timeDay'\n" +
            "        ) as lastWeek\n" +
            "    ) as t1\n" +
            "    LEFT JOIN (\n" +
            "        SELECT\n" +
            "            DATE_FORMAT(production_star.create_time,'%Y-%m-%d') time ,COUNT(production_star.id) as number\n" +
            "        FROM sentence , production_star where production_star.type = '放空句子' and sentence.id = production_star.obj_id and production_star.del = 0 and sentence.del = 0\n" +
            "        GROUP BY DATE_FORMAT(create_time,'%Y-%m-%d')\n" +
            "    ) as t2 ON t1.timeDay = t2.time\n" +
            "    ORDER BY t1.timeDay")
    public List<DataVisualizationDateAndInteger> getSentenceStarAWeekDataVisualization();

    @Select("SELECT \n" +
            "        t1.timeDay as time,t2.number\n" +
            "    FROM (\n" +
            "        SELECT\n" +
            "            date_format(lastWeek.`timeDay`,'%Y-%m-%d') as 'timeDay'\n" +
            "        FROM (\n" +
            "            select DATE_SUB(NOW(),interval 7 day) as 'timeDay'\n" +
            "            UNION ALL\n" +
            "            select DATE_SUB(NOW(),interval 6 day) as 'timeDay'\n" +
            "            UNION ALL\n" +
            "            select DATE_SUB(NOW(),interval 5 day) as 'timeDay'\n" +
            "            UNION ALL\n" +
            "            select DATE_SUB(NOW(),interval 4 day) as 'timeDay'\n" +
            "            UNION ALL\n" +
            "            select DATE_SUB(NOW(),interval 3 day) as 'timeDay'\n" +
            "            UNION ALL\n" +
            "            select DATE_SUB(NOW(),interval 2 day) as 'timeDay'\n" +
            "            UNION ALL\n" +
            "            select DATE_SUB(NOW(),interval 1 day) as 'timeDay'\n" +
            "            UNION ALL\n" +
            "            select NOW() as 'timeDay'\n" +
            "        ) as lastWeek\n" +
            "    ) as t1\n" +
            "    LEFT JOIN (\n" +
            "        SELECT\n" +
            "            DATE_FORMAT(production_collection.create_time,'%Y-%m-%d') time ,COUNT(production_collection.id) as number\n" +
            "        FROM sentence , production_collection where production_collection.type = '放空句子' and sentence.id = production_collection.obj_id and production_collection.del = 0 and sentence.del = 0\n" +
            "        GROUP BY DATE_FORMAT(create_time,'%Y-%m-%d')\n" +
            "    ) as t2 ON t1.timeDay = t2.time\n" +
            "    ORDER BY t1.timeDay")
    public List<DataVisualizationDateAndInteger> getSentenceCollectAWeekDataVisualization();

    @Select("SELECT\n" +
            "        t1.timeDay as time,t2.number\n" +
            "    FROM (\n" +
            "        SELECT\n" +
            "            date_format(lastWeek.`timeDay`,'%Y-%m-%d') as 'timeDay'\n" +
            "        FROM (\n" +
            "            select DATE_SUB(NOW(),interval 7 day) as 'timeDay'\n" +
            "            UNION ALL\n" +
            "            select DATE_SUB(NOW(),interval 6 day) as 'timeDay'\n" +
            "            UNION ALL\n" +
            "            select DATE_SUB(NOW(),interval 5 day) as 'timeDay'\n" +
            "            UNION ALL\n" +
            "            select DATE_SUB(NOW(),interval 4 day) as 'timeDay'\n" +
            "            UNION ALL\n" +
            "            select DATE_SUB(NOW(),interval 3 day) as 'timeDay'\n" +
            "            UNION ALL\n" +
            "            select DATE_SUB(NOW(),interval 2 day) as 'timeDay'\n" +
            "            UNION ALL\n" +
            "            select DATE_SUB(NOW(),interval 1 day) as 'timeDay'\n" +
            "            UNION ALL\n" +
            "            select NOW() as 'timeDay'\n" +
            "        ) as lastWeek\n" +
            "    ) as t1\n" +
            "    LEFT JOIN (\n" +
            "        SELECT\n" +
            "            DATE_FORMAT(observe.create_time,'%Y-%m-%d') time ,COUNT(observe.id) as number\n" +
            "        FROM sentence , observe where observe.type = '放空句子' and sentence.id = observe.obj_id and observe.del = 0 and sentence.del = 0\n" +
            "        GROUP BY DATE_FORMAT(create_time,'%Y-%m-%d')\n" +
            "    ) as t2 ON t1.timeDay = t2.time\n" +
            "    ORDER BY t1.timeDay")
    public List<DataVisualizationDateAndInteger> getSentenceObserveAWeekDataVisualization();

/*    @Select("select sentence.*\n" +
            "from  sentence  where sentence.del = 0 and sentence.state = '审批通过'\n" +
            "and sentence.see = true and\n" +
            "CONCAT_WS(sentence.content , sentence.original_author , sentence.author_name , sentence.author_name)\n" +
            "like CONCAT('%' , #{sentenceKeyValue} , '%')\n" +
            "order by create_time DESC ;")
    public List<Sentence> getSentenceByKeyValue(String sentenceKeyValue);*/
}

