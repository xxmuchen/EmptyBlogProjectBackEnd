package com.example.emptyblogproject.mapper.griphicmapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.emptyblogproject.bean.dairy.Diary;
import com.example.emptyblogproject.bean.griphic.Griphic;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: 王程翔
 * Date: 2022/3/4
 * Time: 10:11
 * Description:
 */
public interface GriphicMapper extends BaseMapper<Griphic> {

    @Select("select griphic.* from userservice , griphic , production_star where userservice.id = production_star.user_id\n" +
            "                and production_star.obj_id = griphic.id and userservice.id = #{userId} \n" +
            "                and userservice.del = false and griphic.del = false and production_star.del = false\n" +
            "                order by production_star.create_time DESC")
    public List<Griphic> getUserSpaceGriphicUserStarOrderByCreateTime(Long userId);

    @Select("select griphic.* from userservice , griphic , production_collection where userservice.id = production_collection.user_id\n" +
            "                and production_collection.obj_id = griphic.id and userservice.id = #{userId} \n" +
            "                and userservice.del = false and griphic.del = false and production_collection.del = false\n" +
            "                order by production_collection.create_time DESC")
    public List<Griphic> getUserSpaceGriphicUserCollectionOrderByCreateTime(Long userId);

}
