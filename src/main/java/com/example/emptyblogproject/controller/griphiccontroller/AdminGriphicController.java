package com.example.emptyblogproject.controller.griphiccontroller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.emptyblogproject.bean.dairy.Diary;
import com.example.emptyblogproject.bean.griphic.Griphic;
import com.example.emptyblogproject.service.griphicservice.GriphicService;
import com.example.emptyblogproject.service.sentenceservice.SentenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public Griphic adminGetGriphicById(@RequestParam(name = "griphicId") Long griphicId) {
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

    /*待审批*/
    @PutMapping("/adminGriphicApproveWait")
    public List<Griphic> adminGriphicApproveWait (@RequestBody String griphicData) {
        JSONObject jsonObject = JSON.parseObject(griphicData);
        Griphic griphic = griphicService.getById(jsonObject.getLong("griphicId"));
        griphic.setState("待审批");
        griphic.setErrorReason(null);
        griphicService.updateById(griphic);
        List<Griphic> griphicList = griphicService.adminGetAllGriphicByPageAndCreateTime();
        return griphicList;
    }
    /*审批通过*/
    @PutMapping("/adminGriphicApproveSuccess")
    public List<Griphic> adminGriphicApproveSuccess(@RequestBody String griphicData) {
        JSONObject jsonObject = JSON.parseObject(griphicData);
        Griphic griphic = griphicService.getById(jsonObject.getLong("griphicId"));
        griphic.setState("审批通过");
        griphic.setErrorReason(null);
        griphicService.updateById(griphic);
//        griphicService.update(griphic.setErrorReason(null))
        List<Griphic> griphicList = griphicService.adminGetAllGriphicByPageAndCreateTime();
        return griphicList;
    }
    //    审批不通过
    @PutMapping("/adminGriphicApproveFail")
    public List<Griphic> adminGriphicApproveFail(@RequestBody String griphicData) {
        JSONObject jsonObject = JSON.parseObject(griphicData);
        Griphic griphic = griphicService.getById(jsonObject.getLong("griphicId"));
        griphic.setState("审批不通过");
        griphic.setErrorReason(jsonObject.getString("errorReason"));
        griphicService.updateById(griphic);
        List<Griphic> griphicList = griphicService.adminGetAllGriphicByPageAndCreateTime();
        return griphicList;
    }
}
