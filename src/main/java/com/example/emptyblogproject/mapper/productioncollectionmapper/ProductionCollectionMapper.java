package com.example.emptyblogproject.mapper.productioncollectionmapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.emptyblogproject.bean.productioncollection.ProductionCollection;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface ProductionCollectionMapper extends BaseMapper<ProductionCollection> {
    /*重新收藏日记*/
    @Update("update production_collection set del = 0 where id = #{id}")
    public boolean reCollectDiary(Long id);

    /*查询取消收藏的记录*/
    @Select("select * from production_collection where user_id = #{userId} and obj_id = #{objId} and type = #{type} and del = 1")
    public ProductionCollection getOneHasDelDiaryCollection(Long userId , Long objId , String type);



}
