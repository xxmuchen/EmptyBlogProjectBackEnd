package com.example.emptyblogproject.controller.diarycontroller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.emptyblogproject.bean.dairy.Diary;
import com.example.emptyblogproject.service.diaryservice.DiaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: 王程翔
 * Date: 2022/3/14
 * Time: 14:51
 * Description:
 */
@RestController
public class AdminDiaryController {

    @Autowired
    DiaryService diaryService;
    /*分页查询所有日记*/
    @GetMapping("/adminGetAllDiaryByPageAndCreateTime")
    public Page<Diary> adminGetAllDiaryByPageAndCreateTime(@RequestParam(name = "currentPage") int currentPage) {
        Page<Diary> diaryPage = diaryService.adminGetAllDiaryByPageAndCreateTime(currentPage);
        return diaryPage;
    }
    /*根据id获取数据*/
    @GetMapping("/adminGetDiaryById")
    public Diary adminGetDiaryById(@RequestParam(name = "diaryId") Long diaryId) {
        Diary diary = diaryService.getById(diaryId);
        return diary;
    }
//    根据id删除数据
    @DeleteMapping("/adminDeleteDiaryById")
    public Page<Diary> adminDeleteDiaryById(@RequestParam(name = "diaryId") Long diaryId) {
        boolean flag = diaryService.removeById(diaryId);

        if (flag) {
            Page<Diary> diaryPage = diaryService.adminGetAllDiaryByPageAndCreateTime(1);
            return diaryPage;
        }else {
            throw new RuntimeException("删除错误，请重试");
        }
    }
}
