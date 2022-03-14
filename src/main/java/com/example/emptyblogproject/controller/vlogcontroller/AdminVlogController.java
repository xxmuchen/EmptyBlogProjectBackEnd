package com.example.emptyblogproject.controller.vlogcontroller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.emptyblogproject.bean.sentence.Sentence;
import com.example.emptyblogproject.bean.vlog.Vlog;
import com.example.emptyblogproject.service.sentenceservice.SentenceService;
import com.example.emptyblogproject.service.vlogservice.VlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with IntelliJ IDEA.
 * User: 王程翔
 * Date: 2022/3/14
 * Time: 21:25
 * Description:
 */
@RestController
public class AdminVlogController {

    @Autowired
    VlogService vlogService;
    /*分页查询所有句子记*/
    @GetMapping("/adminGetAllVlogByPageAndCreateTime")
    public Page<Vlog> adminGetAllVlogByPageAndCreateTime(@RequestParam(name = "currentPage") int currentPage) {
        Page<Vlog> vlogPage = vlogService.adminGetAllVlogByPageAndCreateTime(currentPage);
        return vlogPage;
    }
    /*根据id获取数据*/
    @GetMapping("/adminGetVlogById")
    public Vlog adminGetDiaryById(@RequestParam(name = "vlogId") Long vlogId) {
        Vlog vlog = vlogService.getById(vlogId);
        
//        Diary diary = diaryService.getById(diaryId);
        return vlog;
    }
    //    根据id删除数据
    @DeleteMapping("/adminDeleteVlogById")
    public Page<Vlog> adminDeleteVlogById(@RequestParam(name = "vlogId") Long vlogId) {
        boolean flag = vlogService.removeById(vlogId);

        if (flag) {
            Page<Vlog> vlogPage = vlogService.adminGetAllVlogByPageAndCreateTime(1);
            return vlogPage;
        }else {
            throw new RuntimeException("删除错误，请重试");
        }
    }
}
