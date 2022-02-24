package com.example.emptyblogproject.service.diaryservice;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.emptyblogproject.bean.dairy.Diary;

import java.util.List;

public interface DiaryService extends IService<Diary> {

//    获取最新三条日记数据
    public List<Diary> getNewDiaryFourPieces();

    /*分页查询最新日记*/
    public Page<Diary> getNewDiaryListPageing(int currentPage);

    /*分页查询推荐日记——以收藏数量排序*/
    public Page<Diary> getRecommendDiaryListPageing(int currentPage);

    /*分页查询顶客排行——以点赞数量排序*/
    public Page<Diary> getTopGuestDiaryListPageing(int currentPage);
}
