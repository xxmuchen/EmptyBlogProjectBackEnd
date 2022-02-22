package com.example.emptyblogproject.mapper.observemapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.emptyblogproject.bean.observe.Observe;
import com.example.emptyblogproject.bean.observe.observeBo.ObserveNodeBO;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: 王程翔
 * Date: 2022/2/23
 * Time: 3:05
 * Description: 评论的mapper
 */
@Mapper
public interface ObserveMapper extends BaseMapper<Observe> {

    /**
     * 功能描述：根据博客id和lastId为空，查询所有的一级评论信息集合
     * @param
     * @return 一级评论信息集合
     * @author 王程翔
     * Date: 2020/4/16 10:37
     */
    @Select("SELECT * FROM observe LEFT JOIN user " +
            "ON observe.observer_id = user.id " +
            "WHERE observe.type = #{type} AND observe.obj_id = #{objId} AND observe.del = 0 AND observe.last_id is null")
    @Results({
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "type" , property = "type"),
            @Result(column = "obj_id", property = "objId"),
            @Result(column = "observer_id", property = "observerId"),
            @Result(column = "observe_content", property = "observeContent"),
            @Result(column = "observer_id", property = "user",
                    one = @One(select = "com.example.emptyblogproject.mapper.UsersMapper.UserMapper.selectById",
                            fetchType = FetchType.EAGER)),
            @Result(column = "last_id", property = "lastId"),
            @Result(column = "del", property = "del"),
            @Result(column = "create_time", property = "createTime"),
            @Result(column = "update_time", property = "updateTime")
    })
    List<ObserveNodeBO> queryFirstObserveList (String type , Long objId);


    /**
     * 功能描述：根据obj_id和lastId不为空，查询所有的二级评论信息集合
     * @param
     * @return 二级评论信息集合
     * @author RenShiWei
     * Date: 2020/4/16 10:37
     */
    @Select("SELECT * FROM observe LEFT JOIN user " +
            "ON observe.observer_id=user.id " +
            "WHERE observe.type = #{type} AND observe.obj_id=#{objId} AND observe.del = 0 AND observe.last_id is not null")
    @Results({
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "type" , property = "type"),
            @Result(column = "obj_id", property = "objId"),
            @Result(column = "observer_id", property = "observerId"),
            @Result(column = "observe_content", property = "observeContent"),
            @Result(column = "observer_id", property = "user",
                    one = @One(select = "com.example.emptyblogproject.mapper.UsersMapper.UserMapper.selectById",
                            fetchType = FetchType.EAGER)),
            @Result(column = "last_id", property = "lastId"),
            @Result(column = "del", property = "del"),
            @Result(column = "create_time", property = "createTime"),
            @Result(column = "update_time", property = "updateTime")
    })
    List<ObserveNodeBO> querySecondObserveList (String type , Long objId);
}
