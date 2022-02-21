package com.example.emptyblogproject.mapper.diarymapper.diarystarmapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.emptyblogproject.bean.dairy.diarystar.DiaryStar;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface DiaryStarMapper extends BaseMapper<DiaryStar> {

    /*取消日记点赞*/
//    @Update("update diary_star set del = 1 where user_id = #{userId} and diary_id = #{diaryId}")
//    public boolean cancelDiaryStar(Long userId , Long diaryId);

    /*重新点赞日记*/
    @Update("update diary_star set del = 0 where id = #{id}")
    public boolean reLikeDiary(Long id);
}
