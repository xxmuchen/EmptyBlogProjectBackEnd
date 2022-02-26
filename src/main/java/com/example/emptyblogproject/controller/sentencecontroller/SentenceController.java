package com.example.emptyblogproject.controller.sentencecontroller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.emptyblogproject.annotation.UserLoginToken;
import com.example.emptyblogproject.bean.dairy.Diary;
import com.example.emptyblogproject.bean.productioncollection.ProductionCollection;
import com.example.emptyblogproject.bean.productionstar.ProductionStar;
import com.example.emptyblogproject.bean.sentence.Sentence;
import com.example.emptyblogproject.bean.sentence.sentencetag.SentenceTag;
import com.example.emptyblogproject.bean.user.User;
import com.example.emptyblogproject.service.productioncollectionservice.ProductionCollectionService;
import com.example.emptyblogproject.service.productionstarservice.ProductionStarService;
import com.example.emptyblogproject.service.sentenceservice.SentenceService;
import com.example.emptyblogproject.service.sentenceservice.sentencetagservice.SentenceTagService;
import com.example.emptyblogproject.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: 王程翔
 * Date: 2022/2/25
 * Time: 0:55
 * Description:
 */
@RestController
public class SentenceController {

    @Autowired
    SentenceService sentenceService;
    @Autowired
    SentenceTagService sentenceTagService;
    @Autowired
    TokenUtils tokenUtils;
    @Autowired
    ProductionStarService productionStarService;
    @Autowired
    ProductionCollectionService productionCollectionService;

//    添加句子
    @PostMapping("/addSentence")
    @Transactional
    @UserLoginToken
    public String addSentence(@RequestBody String sentenceData , HttpServletRequest httpServletRequest) {
        String authorization = httpServletRequest.getHeader("Authorization");
        User user = tokenUtils.parseTokenAndGetUser(authorization);

        if (user == null) {
            throw new RuntimeException("用户不存在，请重新登录");
        }
//        System.out.println(sentenceData);
        JSONObject jsonObject = JSON.parseObject(sentenceData);
        Sentence sentence = new Sentence();
        sentence.setContent((String) jsonObject.get("content"));
        sentence.setSee((Boolean) jsonObject.get("see"));
        sentence.setOriginalAuthor((String) jsonObject.get("originalAuthor"));
        sentence.setBgColor((String) jsonObject.get("bgColor"));
        String sentenceId = System.currentTimeMillis() + UUID.randomUUID().toString().replace("-", "");
        sentence.setSentenceId(sentenceId);
        sentence.setAuthorAvatar(user.getAvatar());
        sentence.setAuthorName(user.getUserName());
        sentence.setAuthorId(user.getId());
        JSONArray sentenceTagJsonArray = jsonObject.getJSONArray("sentenceTagList");
        List<SentenceTag> sentenceTagList = new ArrayList<>();
        for (int i = 0 ; i < sentenceTagJsonArray.size() ; i++) {
            SentenceTag sentenceTag = null;
            sentenceTag = new SentenceTag();
            sentenceTag.setTagName(sentenceTagJsonArray.getString(i));
            sentenceTag.setSentenceId(sentenceId);
            boolean flag = sentenceTagService.save(sentenceTag);
            sentenceTagList.add(sentenceTag);
        }
        sentence.setSentenceTagList(sentenceTagList);
//        sentence.setSentenceTagList();
//        System.out.println(sentence);

        boolean flag = sentenceService.save(sentence);
        if (flag) {
            return "上传成功";
        }else {
            throw new RuntimeException("上传失败，请重试");
        }
    }

    @GetMapping("/getAllSentence")
    public List<Sentence> getAllSentence() {
        List<Sentence> allSentence = sentenceService.getAllSentence();
        return allSentence;
    }

    /*获取首页九个标签*/
    @GetMapping("/getNineTags")
    public List<SentenceTag> getNineTags() {
        List<SentenceTag> sentenceTags = sentenceTagService.getTagsOrderByCount();

        if (sentenceTags.size() > 9) {
            return sentenceTags.subList(0 , 9);
        }

        return sentenceTags;
    }

    /*获取偶遇佳句*/
    @GetMapping("/encounterLoverSentence")
    public Sentence encounterLoverSentence() {
        List<Sentence> sentenceList = sentenceService.getAllSentence();
        Random random = new Random();
        int nextInt = random.nextInt(sentenceList.size());
        return sentenceList.get(nextInt);
    }

    /*获取名人名言*/
    @GetMapping("/quotesByFamousPeople")
    public List<Sentence> quotesByFamousPeople() {
        List<Sentence> sentenceList = sentenceService.quotesByFamousPeople();
//        System.out.println(sentenceList);
        return sentenceList;
    }

    /*获取精选句集*/
    @GetMapping("/recommendSentenceList")
    public List<Sentence> recommendSentenceList() {
        List<Sentence> sentenceList = sentenceService.getRecommendSentenceList();
        return sentenceList;
    }

    @GetMapping("/getOneSentenceById")
    public Sentence getOneSentenceById(@RequestParam(name = "sentenceId") Long sentenceId) {
        Sentence sentence = sentenceService.getById(sentenceId);

        return sentence;
    }

}
