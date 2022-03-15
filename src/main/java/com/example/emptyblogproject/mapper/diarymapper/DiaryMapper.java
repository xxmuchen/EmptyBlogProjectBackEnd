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
}
