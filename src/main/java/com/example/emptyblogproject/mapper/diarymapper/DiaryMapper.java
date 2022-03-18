package com.example.emptyblogproject.mapper.diarymapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.emptyblogproject.bean.dairy.Diary;
import com.example.emptyblogproject.bean.datavisualization.DataVisualizationDateAndInteger;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DiaryMapper extends BaseMapper<Diary> {

    /*分页查询推荐日记——以收藏数量排序*/
    @Select("select   diary.* \n" +
            "                    from diary left join (select production_collection.obj_id , count(*) as count , del\n" +
            "                    from production_collection where del = 0\n" +
            "                    group by obj_id , del\n" +
            "                    order by count DESC) as pc  on  pc.obj_id  = diary.id where diary.del = 0\n" +
            "                    order by pc.count DESC , diary.create_time DESC")
    public Page<Diary> getRecommendDiaryListPageing(Page<Diary> pagination);


    /*分页查询顶客排行——以点赞数量排序*/
    @Select("select   diary.* from diary left join (select production_star.obj_id , count(*) as count , del\n" +
            "                    from production_star where del = 0\n" +
            "                    group by obj_id , del\n" +
            "                    order by count DESC) as ps on  ps.obj_id  = diary.id where diary.del = 0\n" +
            "                    order by ps.count DESC , diary.create_time DESC")
    public Page<Diary> getTopGuestDiaryListPageing(Page<Diary> pagination);


    @Select("select diary.* from userservice , diary , production_star where userservice.id = production_star.user_id\n" +
            "                and production_star.obj_id = diary.id and userservice.id = #{userId} \n" +
            "                and userservice.del = false and diary.del = false and production_star.del = false\n" +
            "                order by production_star.create_time DESC")
    public Page<Diary> getUserSpaceDiaryUserStarOrderByCreateTime(Page<Diary> pagination , Long userId);

    @Select("select diary.* from userservice , diary , production_collection where userservice.id = production_collection.user_id\n" +
            "                and production_collection.obj_id = diary.id and userservice.id = #{userId} \n" +
            "                and userservice.del = false and diary.del = false and production_collection.del = false\n" +
            "                order by production_collection.create_time DESC")
    public Page<Diary> getUserSpaceDiaryUserCollectionOrderByCreateTime(Page<Diary> pagination , Long userId);

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
            "    ) as t1 \n" +
            "    LEFT JOIN (\n" +
            "        SELECT\n" +
            "            DATE_FORMAT(create_time,'%Y-%m-%d') time ,COUNT(*) as number\n" +
            "        FROM diary where diary.del = 0\n" +
            "        GROUP BY DATE_FORMAT(create_time,'%Y-%m-%d')\n" +
            "    ) as t2 ON t1.timeDay = t2.time\n" +
            "    ORDER BY t1.timeDay")
    public List<DataVisualizationDateAndInteger> getDiaryWriteAWeekDataVisualization();

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
            "            DATE_FORMAT(observe.create_time,'%Y-%m-%d') time ,COUNT(observe.id) as number\n" +
            "        FROM diary , observe where observe.type = '放空日记' and diary.id = observe.obj_id and observe.del = 0 and diary.del = 0\n" +
            "        GROUP BY DATE_FORMAT(create_time,'%Y-%m-%d')\n" +
            "    ) as t2 ON t1.timeDay = t2.time\n" +
            "    ORDER BY t1.timeDay")
    public List<DataVisualizationDateAndInteger> getDiaryObserveAWeekDataVisualization();

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
            "            DATE_FORMAT(production_star.create_time,'%Y-%m-%d') time ,COUNT(production_star.id) as number\n" +
            "        FROM diary , production_star where production_star.type = '放空日记' and diary.id = production_star.obj_id and production_star.del = 0 and diary.del = 0\n" +
            "        GROUP BY DATE_FORMAT(create_time,'%Y-%m-%d')\n" +
            "    ) as t2 ON t1.timeDay = t2.time\n" +
            "    ORDER BY t1.timeDay")
    public List<DataVisualizationDateAndInteger> getDiaryStarAWeekDataVisualization();

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
            "        FROM diary , production_collection where production_collection.type = '放空日记' and diary.id = production_collection.obj_id and production_collection.del = 0 and diary.del = 0\n" +
            "        GROUP BY DATE_FORMAT(create_time,'%Y-%m-%d')\n" +
            "    ) as t2 ON t1.timeDay = t2.time\n" +
            "    ORDER BY t1.timeDay")
    public List<DataVisualizationDateAndInteger> getDiaryCollectAWeekDataVisualization();
}
