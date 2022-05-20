package com.example.emptyblogproject.service.diaryservice;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.emptyblogproject.bean.dairy.Diary;
import com.example.emptyblogproject.bean.datavisualization.bo.DataVisualizationBO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
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

    /*分页查询用户个人空间所有日记*/
    public Page<Diary> getUserSpaceDiaryOrderCreateTime(int currentPage , Long userId);

    /*获取审核通过的日记*/
    public Page<Diary> getUserSpaceDiaryStateSuccessOrderCreateTime(int currentPage , Long userId);

    /*获取待审核日记*/
    public Page<Diary> getUserSpaceDiaryStateWaitOrderCreateTime(int currentPage , Long userId);

    /*获取审核失败日记*/
    public Page<Diary> getUserSpaceDiaryStateFailOrderCreateTime(int currentPage , Long userId);

        /*获取用户点赞的日记  按点赞时间排序*/
    public Page<Diary> getUserSpaceDiaryUserStarOrderByCreateTime(int currentPage , Long userId);

    public Page<Diary> getUserSpaceDiaryUserCollectionOrderByCreateTime(int currentPage , Long userId);

    /*管理员获取所有日记*/
    public Page<Diary> adminGetAllDiaryByPageAndCreateTime(int currentPage);

    public DataVisualizationBO getDiaryWriteAWeekDataVisualization();

    public DataVisualizationBO getDiaryObserveAWeekDataVisualization();

    public DataVisualizationBO getDiaryStarAWeekDataVisualization();

    public DataVisualizationBO getDiaryCollectAWeekDataVisualization();

    /*敏感词检测*/
    public List<Diary> getDiaryByKeyValue(String diaryKeyValue);

    /*获取用户所有日记*/
    public List<Diary> getAllDiaryStateSuccessByUserId(Long userId);


    /*管理员获取所有待审批日记*/
//    public Page<Diary> getAllStateWaitDiary(int currentPage);
//    /*管理员获取所有审批通过日记*/
//    public Page<Diary> getAllStateSuccessDiary(int currentPage);
//    /*管理员获取所有审批不通过日记*/
//    public Page<Diary> getAllStateFailDiary(int currentPage);

}
