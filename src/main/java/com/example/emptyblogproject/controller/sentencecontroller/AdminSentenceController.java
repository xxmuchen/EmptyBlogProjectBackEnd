package com.example.emptyblogproject.controller.sentencecontroller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.emptyblogproject.bean.dairy.Diary;
import com.example.emptyblogproject.bean.sentence.Sentence;
import com.example.emptyblogproject.service.diaryservice.DiaryService;
import com.example.emptyblogproject.service.sentenceservice.SentenceService;
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
public class AdminSentenceController {

    @Autowired
    SentenceService sentenceService;
    /*分页查询所有句子*/
    @GetMapping("/adminGetAllSentenceByPageAndCreateTime")
    public Page<Sentence> adminGetAllSentenceByPageAndCreateTime(@RequestParam(name = "currentPage") int currentPage) {
        Page<Sentence> sentencePage = sentenceService.adminGetAllSentenceByPageAndCreateTime(currentPage);
        return sentencePage;
    }
    /*根据id获取数据*/
    @GetMapping("/adminGetSentenceById")
    public Sentence adminGetDiaryById(@RequestParam(name = "sentenceId") Long sentenceId) {
        Sentence sentence = sentenceService.getById(sentenceId);

//        Diary diary = diaryService.getById(diaryId);
        return sentence;
    }
    //    根据id删除数据
    @DeleteMapping("/adminDeleteSentenceById")
    public Page<Sentence> adminDeleteSentenceById(@RequestParam(name = "sentenceId") Long sentenceId) {
        boolean flag = sentenceService.removeById(sentenceId);

        if (flag) {
            Page<Sentence> diaryPage = sentenceService.adminGetAllSentenceByPageAndCreateTime(1);
            return diaryPage;
        }else {
            throw new RuntimeException("删除错误，请重试");
        }
    }

    /*待审批*/
    @PutMapping("/adminSentenceApproveWait")
    public Page<Sentence> adminSentenceApproveWait (@RequestBody String sentenceData) {
        JSONObject jsonObject = JSON.parseObject(sentenceData);
        Sentence sentence = sentenceService.getById(jsonObject.getLong("sentenceId"));
        sentence.setState("待审批");
        sentence.setErrorReason(null);
        sentenceService.updateById(sentence);
        Page<Sentence> sentencePage = sentenceService.adminGetAllSentenceByPageAndCreateTime(1);
        return sentencePage;
    }
    /*审批通过*/
    @PutMapping("/adminSentenceApproveSuccess")
    public Page<Sentence> adminSentenceApproveSuccess(@RequestBody String sentenceData) {
        JSONObject jsonObject = JSON.parseObject(sentenceData);
        Sentence sentence = sentenceService.getById(jsonObject.getLong("sentenceId"));
        sentence.setState("审批通过");
        sentence.setErrorReason(null);
        sentenceService.updateById(sentence);
        Page<Sentence> sentencePage = sentenceService.adminGetAllSentenceByPageAndCreateTime(1);
        return sentencePage;
    }
    //    审批不通过
    @PutMapping("/adminSentenceApproveFail")
    public Page<Sentence> adminSentenceApproveFail(@RequestBody String sentenceData) {
        JSONObject jsonObject = JSON.parseObject(sentenceData);
        Sentence sentence = sentenceService.getById(jsonObject.getLong("sentenceId"));
        sentence.setState("审批不通过");
        sentence.setErrorReason(jsonObject.getString("errorReason"));
        sentenceService.updateById(sentence);
        Page<Sentence> sentencePage = sentenceService.adminGetAllSentenceByPageAndCreateTime(1);
        return sentencePage;
    }
}
