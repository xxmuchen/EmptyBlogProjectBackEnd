package com.example.emptyblogproject.mapper.productionstarmapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.emptyblogproject.bean.productionstar.ProductionStar;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface ProductionStarMapper extends BaseMapper<ProductionStar> {

    /*取消日记点赞*/
//    @Update("update diary_star set del = 1 where user_id = #{userId} and diary_id = #{diaryId}")
//    public boolean cancelDiaryStar(Long userId , Long diaryId);

    /*重新点赞日记*/
    @Update("update production_star set del = 0 where id = #{id}")
    public boolean reLikeDiary(Long id);

    /*查询取消点赞的记录*/
    @Select("select * from production_star where user_id = #{userId} and obj_id = #{objId} and type = #{type} and del = 1")
    public ProductionStar getOneHasDelDiaryStar(Long userId , Long objId , String type);
}
