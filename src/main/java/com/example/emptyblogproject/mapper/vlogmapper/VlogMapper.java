package com.example.emptyblogproject.mapper.vlogmapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.emptyblogproject.bean.dairy.Diary;
import com.example.emptyblogproject.bean.vlog.Vlog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

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
}
