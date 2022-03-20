package com.example.emptyblogproject.controller.sentencecontroller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.emptyblogproject.annotation.UserLoginToken;
import com.example.emptyblogproject.bean.sensitivewords.SensitiveWords;
import com.example.emptyblogproject.bean.sentence.Sentence;
import com.example.emptyblogproject.bean.sentence.sentencetag.SentenceTag;
import com.example.emptyblogproject.bean.user.User;
import com.example.emptyblogproject.service.productioncollectionservice.ProductionCollectionService;
import com.example.emptyblogproject.service.productionstarservice.ProductionStarService;
import com.example.emptyblogproject.service.sensitivewordsservice.SensitiveWordsService;
import com.example.emptyblogproject.service.sentenceservice.SentenceService;
import com.example.emptyblogproject.service.sentenceservice.sentencetagservice.SentenceTagService;
import com.example.emptyblogproject.utils.UserTokenUtils;
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
    UserTokenUtils userTokenUtils;
    @Autowired
    ProductionStarService productionStarService;
    @Autowired
    ProductionCollectionService productionCollectionService;
    @Autowired
    SensitiveWordsService sensitiveWordsService;
    /*添加句子*/
    @PostMapping("/addSentence")
    @Transactional
    @UserLoginToken
    public String addSentence(@RequestBody String sentenceData , HttpServletRequest httpServletRequest) {
        String authorization = httpServletRequest.getHeader("Authorization");
        User user = userTokenUtils.parseTokenAndGetUser(authorization);

        if (user == null) {
            throw new RuntimeException("用户不存在，请重新登录");
        }
        JSONObject jsonObject = JSON.parseObject(sentenceData);
        Sentence sentence = new Sentence();
        sentence.setContent( jsonObject.getString("content"));
        sentence.setSee( jsonObject.getBoolean("see"));
        sentence.setOriginalAuthor( jsonObject.getString("originalAuthor"));
        sentence.setBgColor( jsonObject.getString("bgColor"));
        String sentenceSentenceId = System.currentTimeMillis() + UUID.randomUUID().toString().replace("-", "");
        sentence.setSentenceId(sentenceSentenceId);
        sentence.setAuthorAvatar(user.getAvatar());
        sentence.setAuthorName(user.getUserName());
        sentence.setAuthorId(user.getId());
        JSONArray sentenceTagJsonArray = jsonObject.getJSONArray("sentenceTagList");
        List<SentenceTag> sentenceTagList = new ArrayList<>();
        List<SensitiveWords> sensitiveWordsList = sensitiveWordsService.list();
        for (int i = 0 ; i < sentenceTagJsonArray.size() ; i++) {
            SentenceTag sentenceTag = null;
            sentenceTag = new SentenceTag();
            sentenceTag.setTagName(sentenceTagJsonArray.getString(i));
            sentenceTag.setSentenceId(sentenceSentenceId);
            for (SensitiveWords sensitiveWords : sensitiveWordsList) {
                if (sentenceTag.getTagName().contains(sensitiveWords.getSensitiveWord())) {
                    throw new RuntimeException("该标签中含有敏感词:" + sensitiveWords.getSensitiveWord() + ",请修改后重新上传");
                }
            }
            boolean flag = sentenceTagService.save(sentenceTag);
            sentenceTagList.add(sentenceTag);
        }
        sentence.setSentenceTagList(sentenceTagList);

        for (SensitiveWords sensitiveWords : sensitiveWordsList) {
            if (sentence.getContent().contains(sensitiveWords.getSensitiveWord())) {
                throw new RuntimeException("该句子内容中含有敏感词:" + sensitiveWords.getSensitiveWord() + ",请修改后重新上传");
            } else if (sentence.getOriginalAuthor().contains(sensitiveWords.getSensitiveWord())) {
                throw new RuntimeException("该原作者名字中含有敏感词:" + sensitiveWords.getSensitiveWord() + ",请修改后重新上传");
            }
        }

        sentence.setState("待审批");
        boolean flag = sentenceService.save(sentence);
        if (flag) {
            return "上传成功";
        }else {
            throw new RuntimeException("上传失败，请重试");
        }
    }

    @GetMapping("/getAllSentence")
    public List<Sentence> getAllSentence() {
        List<Sentence> allSentence = sentenceService.getAllSentenceBySee();
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
        List<Sentence> sentenceList = sentenceService.getAllSentenceBySee();
        Random random = new Random();
        int nextInt = random.nextInt(sentenceList.size());
        return sentenceList.get(nextInt);
    }

    /*获取名人名言*/
    @GetMapping("/quotesByFamousPeople")
    public List<Sentence> quotesByFamousPeople() {
        List<Sentence> sentenceList = sentenceService.quotesByFamousPeopleBySee();
//        System.out.println(sentenceList);
        return sentenceList;
    }

    /*获取精选句集*/
    @GetMapping("/recommendSentenceList")
    public List<Sentence> recommendSentenceList() {
        List<Sentence> sentenceList = sentenceService.getRecommendSentenceListBySee();
        return sentenceList;
    }

    @GetMapping("/getOneSentenceById")
    public Sentence getOneSentenceById(@RequestParam(name = "sentenceId") Long sentenceId) {
        Sentence sentence = sentenceService.getById(sentenceId);

        List<SentenceTag> sentenceTagList = sentenceTagService.getTagsBySentenceSentenceId(sentence.getSentenceId());

        sentence.setSentenceTagList(sentenceTagList);

        return sentence;
    }

}
