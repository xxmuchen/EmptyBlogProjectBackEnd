package com.example.emptyblogproject.mapper.vlogmapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.emptyblogproject.bean.dairy.Diary;
import com.example.emptyblogproject.bean.datavisualization.DataVisualizationDateAndInteger;
import com.example.emptyblogproject.bean.vlog.Vlog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: 王程翔
 * Date: 2022/2/27
 * Time: 14:53
 * Description:
 */

@Mapper
public interface VlogMapper extends BaseMapper<Vlog> {

     @Select("select vlog.* from userservice , vlog , production_star where userservice.id = production_star.user_id\n" +
            "                and production_star.obj_id = vlog.id and userservice.id = #{userId} \n" +
            "                and userservice.del = false and vlog.del = false and production_star.del = false\n" +
            "                order by production_star.create_time DESC")
    public Page<Vlog> getUserSpaceVlogUserStarOrderByCreateTime(Page<Vlog> pagination , Long userId);

    @Select("select vlog.* from userservice , vlog , production_collection where userservice.id = production_collection.user_id\n" +
            "                and production_collection.obj_id = vlog.id and userservice.id = #{userId} \n" +
            "                and userservice.del = false and vlog.del = false and production_collection.del = false\n" +
            "                order by production_collection.create_time DESC")
    public Page<Vlog> getUserSpaceVlogUserCollectionOrderByCreateTime(Page<Vlog> pagination , Long userId);

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
            "            DATE_FORMAT(vlog.create_time,'%Y-%m-%d') time ,COUNT(vlog.id) as number\n" +
            "        FROM vlog where vlog.del = 0\n" +
            "        GROUP BY DATE_FORMAT(create_time,'%Y-%m-%d')\n" +
            "    ) as t2 ON t1.timeDay = t2.time\n" +
            "    ORDER BY t1.timeDay")
    public List<DataVisualizationDateAndInteger> getVlogWriteAWeekDataVisualization();

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
            "        FROM vlog , production_star where production_star.type = '放空Vlog' and vlog.id = production_star.obj_id and production_star.del = 0 and vlog.del = 0\n" +
            "        GROUP BY DATE_FORMAT(create_time,'%Y-%m-%d')\n" +
            "    ) as t2 ON t1.timeDay = t2.time\n" +
            "    ORDER BY t1.timeDay")
    public List<DataVisualizationDateAndInteger> getVlogStarAWeekDataVisualization();

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
            "        FROM vlog , production_collection where production_collection.type = '放空Vlog' and vlog.id = production_collection.obj_id and production_collection.del = 0 and vlog.del = 0\n" +
            "        GROUP BY DATE_FORMAT(create_time,'%Y-%m-%d')\n" +
            "    ) as t2 ON t1.timeDay = t2.time\n" +
            "    ORDER BY t1.timeDay")
    public List<DataVisualizationDateAndInteger> getVlogCollectAWeekDataVisualization();

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
            "        FROM vlog , observe where observe.type = '放空Vlog' and vlog.id = observe.obj_id and observe.del = 0 and vlog.del = 0\n" +
            "        GROUP BY DATE_FORMAT(create_time,'%Y-%m-%d')\n" +
            "    ) as t2 ON t1.timeDay = t2.time\n" +
            "    ORDER BY t1.timeDay")
    public List<DataVisualizationDateAndInteger> getVlogObserveAWeekDataVisualization();
}
