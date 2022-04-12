package com.example.emptyblogproject.controller.noticecontroller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.emptyblogproject.bean.notice.Notice;
import com.example.emptyblogproject.bean.notice.NoticeConfirm;
import com.example.emptyblogproject.bean.user.User;
import com.example.emptyblogproject.service.noticeconfirmmapper.NoticeConfirmService;
import com.example.emptyblogproject.service.noticemapper.NoticeService;
import com.example.emptyblogproject.utils.UserTokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: 王程翔
 * Date: 2022/4/11
 * Time: 1:36
 * Description:
 */
@RestController
public class NoticeConfirmController {

    @Autowired
    UserTokenUtils userTokenUtils;
    @Autowired
    NoticeConfirmService noticeConfirmService;
    @Autowired
    NoticeService noticeService;

//    @GetMapping("/getNoticeConfirmInfo")
//    public boolean getNoticeConfirmInfo(HttpServletRequest httpServletRequest) {
//        String authorization = httpServletRequest.getHeader("Authorization");
//        User user = userTokenUtils.parseTokenAndGetUser(authorization);
//        boolean noticeConfirmInfo = noticeConfirmService.getNoticeConfirmInfo(user.getId());
//        return noticeConfirmInfo;
//    }

    @PostMapping("/confirmNotice")
    public String confirmNotice(@RequestBody NoticeConfirm noticeConfirm , HttpServletRequest httpServletRequest) {
        String authorization = httpServletRequest.getHeader("Authorization");
        User user = userTokenUtils.parseTokenAndGetUser(authorization);
        noticeConfirm.setUserId(user.getId());
        noticeConfirm.setUserName(user.getUserName());
        boolean flag = noticeConfirmService.save(noticeConfirm);
        if (flag) {
            return "公告确认成功";
        } else {
            return "公告确认失败";
        }
    }

}
