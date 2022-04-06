package com.example.emptyblogproject.controller.vlogcontroller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.emptyblogproject.bean.dairy.Diary;
import com.example.emptyblogproject.bean.sentence.Sentence;
import com.example.emptyblogproject.bean.vlog.Vlog;
import com.example.emptyblogproject.service.sentenceservice.SentenceService;
import com.example.emptyblogproject.service.vlogservice.VlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public Vlog adminGetVlogById(@RequestParam(name = "vlogId") Long vlogId) {
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

    /*待审批*/
    @PutMapping("/adminVlogApproveWait")
    public Page<Vlog> adminVlogApproveWait (@RequestBody String vlogData) {
        JSONObject jsonObject = JSON.parseObject(vlogData);
        Vlog vlog = vlogService.getById(jsonObject.getLong("vlogId"));
        vlog.setState("待审批");
        vlog.setErrorReason(null);
        vlogService.updateById(vlog);
        Page<Vlog> vlogPage = vlogService.adminGetAllVlogByPageAndCreateTime(1);
        return vlogPage;
    }
    /*审批通过*/
    @PutMapping("/adminVlogApproveSuccess")
    public Page<Vlog> adminVlogApproveSuccess(@RequestBody String vlogData) {
        JSONObject jsonObject = JSON.parseObject(vlogData);
        Vlog vlog = vlogService.getById(jsonObject.getLong("vlogId"));
        vlog.setState("审批通过");
        vlog.setErrorReason(null);
        vlogService.updateById(vlog);
        Page<Vlog> diaryPage = vlogService.adminGetAllVlogByPageAndCreateTime(1);
        return diaryPage;
    }
    //    审批不通过
    @PutMapping("/adminVlogApproveFail")
    public Page<Vlog> adminVlogApproveFail(@RequestBody String vlogData) {
        JSONObject jsonObject = JSON.parseObject(vlogData);
        Vlog vlog = vlogService.getById(jsonObject.getLong("vlogId"));
        vlog.setState("审批不通过");
        vlog.setErrorReason(jsonObject.getString("errorReason"));
        vlogService.updateById(vlog);
        Page<Vlog> diaryPage = vlogService.adminGetAllVlogByPageAndCreateTime(1);
        return diaryPage;
    }

}
