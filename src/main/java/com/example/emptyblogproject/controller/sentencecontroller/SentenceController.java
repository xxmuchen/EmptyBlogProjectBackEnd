package com.example.emptyblogproject.controller.sentencecontroller;

import com.example.emptyblogproject.annotation.UserLoginToken;
import com.example.emptyblogproject.bean.sentence.Sentence;
import com.example.emptyblogproject.bean.user.User;
import com.example.emptyblogproject.service.sentenceservice.SentenceService;
import com.example.emptyblogproject.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

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
    TokenUtils tokenUtils;

    @PostMapping("/addSentence")
    @UserLoginToken
    public String addSentence(@RequestBody Sentence sentence , HttpServletRequest httpServletRequest) {
        String authorization = httpServletRequest.getHeader("Authorization");
        User user = tokenUtils.parseTokenAndGetUser(authorization);

        if (user == null) {
            throw new RuntimeException("用户不存在，请重新登录");
        }




        return null;
    }

}
