package com.example.emptyblogproject.controller.diarycontroller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.emptyblogproject.bean.dairy.Diary;
import com.example.emptyblogproject.bean.user.User;
import com.example.emptyblogproject.service.diaryservice.DiaryService;
import com.example.emptyblogproject.service.userservice.UserService;
import com.example.emptyblogproject.utils.UserTokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * Created with IntelliJ IDEA.
 * User: 王程翔
 * Date: 2022/3/7
 * Time: 18:25
 * Description:
 */
@RestController
public class UserSpaceDiaryController {

    @Autowired
    UserTokenUtils userTokenUtils;
    @Autowired
    UserService userService;
    @Autowired
    DiaryService diaryService;

//    获取所有日记
    @GetMapping("/getUserSpaceDiaryOrderCreateTime")
    public Page<Diary> getUserSpaceDiaryOrderByCreateTime(@RequestParam("currentIndex") int currentPage , HttpServletRequest httpServletRequest) {
        String authorization = httpServletRequest.getHeader("authorization");
        User user = userTokenUtils.parseTokenAndGetUser(authorization);
        Page<Diary> diaryPageOrderCreateTime = diaryService.getUserSpaceDiaryOrderCreateTime(currentPage , user.getId());
        return diaryPageOrderCreateTime;
    }

    /*获取审核通过的日记*/
    @GetMapping("/getUserSpaceDiaryStateSuccessOrderCreateTime")
    public Page<Diary> getUserSpaceDiaryStateSuccessOrderCreateTime(@RequestParam("currentIndex") int currentPage , HttpServletRequest httpServletRequest) {
        String authorization = httpServletRequest.getHeader("authorization");
        User user = userTokenUtils.parseTokenAndGetUser(authorization);
        Page<Diary> diaryPageOrderCreateTime = diaryService.getUserSpaceDiaryStateSuccessOrderCreateTime(currentPage , user.getId());
        return diaryPageOrderCreateTime;
    }

    /*获取待审核日记*/
    @GetMapping("/getUserSpaceDiaryStateWaitOrderCreateTime")
    public Page<Diary> getUserSpaceDiaryStateWaitOrderCreateTime(@RequestParam("currentIndex") int currentPage , HttpServletRequest httpServletRequest) {
        String authorization = httpServletRequest.getHeader("authorization");
        User user = userTokenUtils.parseTokenAndGetUser(authorization);
        Page<Diary> diaryPageOrderCreateTime = diaryService.getUserSpaceDiaryStateWaitOrderCreateTime(currentPage , user.getId());
        return diaryPageOrderCreateTime;
    }
    /*获取审核失败日记*/
    @GetMapping("/getUserSpaceDiaryStateFailOrderCreateTime")
    public Page<Diary> getUserSpaceDiaryStateFailOrderCreateTime(@RequestParam("currentIndex") int currentPage , HttpServletRequest httpServletRequest) {
        String authorization = httpServletRequest.getHeader("authorization");
        User user = userTokenUtils.parseTokenAndGetUser(authorization);
        Page<Diary> diaryPageOrderCreateTime = diaryService.getUserSpaceDiaryStateFailOrderCreateTime(currentPage , user.getId());
        return diaryPageOrderCreateTime;
    }

    @DeleteMapping("/delUserSpaceDiaryByDiaryId")
    public Page<Diary> delUserSpaceDiaryByDiaryId(@RequestParam("diaryId") Long diaryId , HttpServletRequest httpServletRequest) {
        String authorization = httpServletRequest.getHeader("authorization");
        User user = userTokenUtils.parseTokenAndGetUser(authorization);
        Diary diary = diaryService.getById(diaryId);
        if (user == null) {
            throw new RuntimeException("用户不存在");
        }
        if (diary == null) {
            throw new RuntimeException("日记不存在");
        }


        if (!diary.getAuthorId().equals(user.getId())) {
            throw new RuntimeException("数据错误，请重试");
        }
        boolean flag = diaryService.removeById(diary.getId());
        if (flag) {
            return diaryService.getUserSpaceDiaryOrderCreateTime(1 , user.getId());
        }else {
            throw new RuntimeException("删除错误,请重试");
        }
    }

    /*获取用户点赞的日记  按点赞时间排序*/
    @GetMapping("/getUserSpaceDiaryUserStarOrderByCreateTime")
    public Page<Diary> getUserSpaceDiaryUserStarOrderByCreateTime(@RequestParam("currentIndex") int currentPage , HttpServletRequest httpServletRequest) {
        String authorization = httpServletRequest.getHeader("authorization");
        User user = userTokenUtils.parseTokenAndGetUser(authorization);
        Page<Diary> userSpaceDiaryUserStarOrderByCreateTime = diaryService.getUserSpaceDiaryUserStarOrderByCreateTime(currentPage, user.getId());
        return userSpaceDiaryUserStarOrderByCreateTime;
    }

    /*获取用户收藏的日记  按点赞时间排序*/
    @GetMapping("/getUserSpaceDiaryUserCollectionOrderByCreateTime")
    public Page<Diary> getUserSpaceDiaryUserCollectionOrderByCreateTime(@RequestParam("currentIndex") int currentPage , HttpServletRequest httpServletRequest) {
        String authorization = httpServletRequest.getHeader("authorization");
        User user = userTokenUtils.parseTokenAndGetUser(authorization);
        Page<Diary> userSpaceDiaryUserCollectionOrderByCreateTime = diaryService.getUserSpaceDiaryUserCollectionOrderByCreateTime(currentPage, user.getId());
        return userSpaceDiaryUserCollectionOrderByCreateTime;
    }
}
