package com.example.emptyblogproject.mapper.UserMapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.emptyblogproject.bean.datavisualization.DataVisualizationDateAndInteger;
import com.example.emptyblogproject.bean.user.UserLogin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: 王程翔
 * Date: 2022/3/15
 * Time: 1:54
 * Description:
 */
@Mapper
public interface UserLoginMapper extends BaseMapper<UserLogin> {

//    获取一周的数据
    @Select("SELECT\n" +
            "    t1.timeDay as time,t2.number\n" +
            "FROM (\n" +
            "    SELECT\n" +
            "        date_format(lastWeek.`timeDay`,'%Y-%m-%d') as 'timeDay'\n" +
            "    FROM (\n" +
            "        select DATE_SUB(NOW(),interval 7 day) as 'timeDay'\n" +
            "        UNION ALL\n" +
            "        select DATE_SUB(NOW(),interval 6 day) as 'timeDay'\n" +
            "        UNION ALL\n" +
            "        select DATE_SUB(NOW(),interval 5 day) as 'timeDay'\n" +
            "        UNION ALL\n" +
            "        select DATE_SUB(NOW(),interval 4 day) as 'timeDay'\n" +
            "        UNION ALL\n" +
            "        select DATE_SUB(NOW(),interval 3 day) as 'timeDay'\n" +
            "        UNION ALL\n" +
            "        select DATE_SUB(NOW(),interval 2 day) as 'timeDay'\n" +
            "        UNION ALL\n" +
            "        select DATE_SUB(NOW(),interval 1 day) as 'timeDay'\n" +
            "        UNION ALL\n" +
            "        select NOW() as 'timeDay'\n" +
            "    ) as lastWeek\n" +
            ") as t1\n" +
            "LEFT JOIN (\n" +
            "    SELECT\n" +
            "        DATE_FORMAT(create_time,'%Y-%m-%d') time ,COUNT(*) as number\n" +
            "    FROM user_login where user_login.del = 0 \n" +
            "    GROUP BY DATE_FORMAT(create_time,'%Y-%m-%d')\n" +
            ") as t2 ON t1.timeDay = t2.time\n" +
            "ORDER BY t1.timeDay")
    public List<DataVisualizationDateAndInteger> getUserLoginAWeekDataVisualization();

}
