package com.example.emptyblogproject.mapper.diarymapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.emptyblogproject.bean.dairy.Diary;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface DiaryMapper extends BaseMapper<Diary> {

    /*分页查询推荐日记——以收藏数量排序*/
    @Select("select   diary.id , diary.title , diary.content ,\n" +
            "       diary.create_time , diary.update_time,\n" +
            "       diary.mood , diary.weather , diary.bg_color , diary.author_name ,\n" +
            "       diary.author_id , diary.see , diary.del\n" +
            "        from (select production_collection.obj_id , count(*) as count , del\n" +
            "        from production_collection where del = 0\n" +
            "        group by obj_id , del\n" +
            "        order by count DESC) as pc right join  diary\n" +
            "        on  pc.obj_id  = diary.id and pc.del = 0 and diary.del = 0 " +
            "        order by pc.count DESC , diary.create_time DESC\n")
    public Page<Diary> getRecommendDiaryListPageing(Page<Diary> pagination);


    /*分页查询顶客排行——以点赞数量排序*/
    @Select("select   diary.id , diary.title , diary.content ,\n" +
            "       diary.create_time , diary.update_time,\n" +
            "       diary.mood , diary.weather , diary.bg_color , diary.author_name ,\n" +
            "       diary.author_id , diary.see , diary.del\n" +
            "        from (select production_star.obj_id , count(*) as count , del\n" +
            "        from production_star where del = 0\n" +
            "        group by obj_id , del\n" +
            "        order by count DESC) as ps right join  diary\n" +
            "        on  ps.obj_id  = diary.id and ps.del = 0 and diary.del = 0 " +
            "        order by ps.count DESC , diary.create_time DESC\n")
    public Page<Diary> getTopGuestDiaryListPageing(Page<Diary> pagination);


    @Select("select diary.* from user , diary , production_star where user.id = production_star.user_id\n" +
            "                and production_star.obj_id = diary.id and user.id = #{userId} \n" +
            "                and user.del = false and diary.del = false and production_star.del = false\n" +
            "                order by production_star.create_time DESC")
    public Page<Diary> getUserSpaceDiaryUserStarOrderByCreateTime(Page<Diary> pagination , Long userId);

    @Select("select diary.* from user , diary , production_collection where user.id = production_collection.user_id\n" +
            "                and production_collection.obj_id = diary.id and user.id = #{userId} \n" +
            "                and user.del = false and diary.del = false and production_collection.del = false\n" +
            "                order by production_collection.create_time DESC")
    public Page<Diary> getUserSpaceDiaryUserCollectionOrderByCreateTime(Page<Diary> pagination , Long userId);
}
