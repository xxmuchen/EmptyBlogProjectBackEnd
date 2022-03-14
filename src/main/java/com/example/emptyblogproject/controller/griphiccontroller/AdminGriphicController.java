package com.example.emptyblogproject.controller.griphiccontroller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.emptyblogproject.bean.griphic.Griphic;
import com.example.emptyblogproject.service.griphicservice.GriphicService;
import com.example.emptyblogproject.service.sentenceservice.SentenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: 王程翔
 * Date: 2022/3/14
 * Time: 21:25
 * Description:
 */
@RestController
public class AdminGriphicController {

    @Autowired
    GriphicService griphicService;
    /*分页查询所有句子*/
    @GetMapping("/adminGetAllGriphicByPageAndCreateTime")
    public List<Griphic> adminGetAllGriphicByPageAndCreateTime() {
        List<Griphic> griphicList = griphicService.adminGetAllGriphicByPageAndCreateTime();
        return griphicList;
    }
    /*根据id获取数据*/
    @GetMapping("/adminGetGriphicById")
    public Griphic adminGetDiaryById(@RequestParam(name = "griphicId") Long griphicId) {
        Griphic griphic = griphicService.getById(griphicId);

        return griphic;
    }
    //    根据id删除数据
    @DeleteMapping("/adminDeleteGriphicById")
    public List<Griphic> adminDeleteGriphicById(@RequestParam(name = "griphicId") Long griphicId) {
        boolean flag = griphicService.removeById(griphicId);
        if (flag) {
            List<Griphic> griphicList = griphicService.adminGetAllGriphicByPageAndCreateTime();
            return griphicList;
        }else {
            throw new RuntimeException("删除错误，请重试");
        }
    }
}
