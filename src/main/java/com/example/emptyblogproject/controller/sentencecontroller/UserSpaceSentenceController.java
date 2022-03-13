package com.example.emptyblogproject.controller.sentencecontroller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.emptyblogproject.bean.sentence.Sentence;
import com.example.emptyblogproject.bean.user.User;
import com.example.emptyblogproject.service.sentenceservice.SentenceService;
import com.example.emptyblogproject.service.user.UserService;
import com.example.emptyblogproject.utils.UserTokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created with IntelliJ IDEA.
 * User: 王程翔
 * Date: 2022/3/8
 * Time: 1:31
 * Description:
 */
@RestController
public class UserSpaceSentenceController {

    @Autowired
    UserService userService;
    @Autowired
    SentenceService sentenceService;
    @Autowired
    UserTokenUtils userTokenUtils;

    @GetMapping("/getUserSpaceSentenceOrderCreateTime")
    public Page<Sentence> getUserSpaceSentenceOrderCreateTime(@RequestParam("currentIndex") int currentPage , HttpServletRequest httpServletRequest) {
        String authorization = httpServletRequest.getHeader("authorization");
        User user = userTokenUtils.parseTokenAndGetUser(authorization);
        Page<Sentence> userSpaceSentenceOrderCreateTime = sentenceService.getUserSpaceSentenceOrderCreateTime(currentPage, user.getId());
        return userSpaceSentenceOrderCreateTime;
    }

    @DeleteMapping("/delUserSpaceSentenceBySentenceId")
    public Page<Sentence> delUserSpaceSentenceBySentenceId(@RequestParam("sentenceId") Long sentenceId , HttpServletRequest httpServletRequest) {
        String authorization = httpServletRequest.getHeader("authorization");
        User user = userTokenUtils.parseTokenAndGetUser(authorization);
        Sentence sentence = sentenceService.getById(sentenceId);
        if (user == null) {
            throw new RuntimeException("用户不存在");
        }
        if (sentence == null) {
            throw new RuntimeException("日记不存在");
        }


        if (!sentence.getAuthorId().equals(user.getId())) {
            throw new RuntimeException("数据错误，请重试");
        }
        boolean flag = sentenceService.removeById(sentence.getId());
        if (flag) {
            return sentenceService.getUserSpaceSentenceOrderCreateTime(1 , user.getId());
        }else {
            throw new RuntimeException("删除错误,请重试");
        }
    }

    /*获取用户点赞的日记  按点赞时间排序*/
    @GetMapping("/getUserSpaceSentenceUserStarOrderByCreateTime")
    public Page<Sentence> getUserSpaceSentenceUserStarOrderByCreateTime(@RequestParam("currentIndex") int currentPage , HttpServletRequest httpServletRequest) {
        String authorization = httpServletRequest.getHeader("authorization");
        User user = userTokenUtils.parseTokenAndGetUser(authorization);
        Page<Sentence> userSpaceSentenceUserStarOrderByCreateTime = sentenceService.getUserSpaceSentenceUserStarOrderByCreateTime(currentPage, user.getId());
        return userSpaceSentenceUserStarOrderByCreateTime;
    }

    /*获取用户收藏的日记  按点赞时间排序*/
    @GetMapping("/getUserSpaceSentenceUserCollectionOrderByCreateTime")
    public Page<Sentence> getUserSpaceSentenceUserCollectionOrderByCreateTime(@RequestParam("currentIndex") int currentPage , HttpServletRequest httpServletRequest) {
        String authorization = httpServletRequest.getHeader("authorization");
        User user = userTokenUtils.parseTokenAndGetUser(authorization);
        Page<Sentence> userSpaceSentenceUserCollectionOrderByCreateTime = sentenceService.getUserSpaceSentenceUserCollectionOrderByCreateTime(currentPage, user.getId());
        return userSpaceSentenceUserCollectionOrderByCreateTime;
    }
}
