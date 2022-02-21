package com.example.emptyblogproject.service.diary.diarystar;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.emptyblogproject.bean.dairy.diarystar.DiaryStar;
import org.apache.ibatis.annotations.Update;

public interface DiaryStarService extends IService<DiaryStar> {

    /*取消日记点赞*/
//    public boolean cancelDiaryStar(Long userId , Long diaryId);

    /*重新点赞日记*/
    public boolean reLikeDiary(Long id);
}
