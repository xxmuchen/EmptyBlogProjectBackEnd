package com.example.emptyblogproject.controller.noticecontroller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.emptyblogproject.annotation.UserLoginToken;
import com.example.emptyblogproject.bean.notice.Notice;
import com.example.emptyblogproject.bean.user.User;
import com.example.emptyblogproject.service.noticeconfirmmapper.NoticeConfirmService;
import com.example.emptyblogproject.service.noticemapper.NoticeService;
import com.example.emptyblogproject.utils.UserTokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: 王程翔
 * Date: 2022/4/10
 * Time: 8:40
 * Description:
 */
@RestController
public class NoticeController {

    @Autowired
    NoticeService noticeService;
    @Autowired
    NoticeConfirmService noticeConfirmService;
    @Autowired
    UserTokenUtils userTokenUtils;

//    获取所有的公告
    @GetMapping("/getAllNoticeByCurrentPage")
    public Page<Notice> getAllNoticeByCurrentPage(@RequestParam(name = "currentPage") int currentPage) {
        Page<Notice> allNoticeByCurrentPage = noticeService.getAllNoticeByCurrentPage(currentPage);
        return allNoticeByCurrentPage;
    }

    @GetMapping("/getOneNoticeDetailInfoById")
    public Notice getOneNoticeDetailInfoById(@RequestParam("noticeId") Long noticeId) {
        Notice notice = noticeService.getById(noticeId);
        return notice;
    }

    @DeleteMapping("/deleteOneNoticeById")
    public String deleteOneNoticeById(@RequestParam("noticeId") Long noticeId) {
        boolean flag = noticeService.removeById(noticeId);
        if (flag) {
            return "公告删除成功";
        } else {
            return "公告删除失败";
        }
    }

    @PostMapping("/addNotice")
    public String addNotice(@RequestBody Notice notice , HttpServletRequest httpServletRequest) {
        String authorization = httpServletRequest.getHeader("Authorization");
        User user = userTokenUtils.parseTokenAndGetUser(authorization);
        notice.setAuthorName(user.getUserName());
        notice.setAuthorId(user.getId());
        boolean flag = noticeService.save(notice);
        if (flag) {
            return "公告添加成功";
        } else {
            return "公告添加失败";
        }
    }

    @GetMapping("getLatestNoticeInfo")
    @UserLoginToken
    public Notice getLatestNoticeInfo(HttpServletRequest httpServletRequest) {
        String authorization = httpServletRequest.getHeader("Authorization");
        User user = userTokenUtils.parseTokenAndGetUser(authorization);
        boolean noticeConfirmInfo = noticeConfirmService.getNoticeConfirmInfo(user.getId());
        if (noticeConfirmInfo) {
            return null;
        }
        Page<Notice> allNoticeByCurrentPage = noticeService.getAllNoticeByCurrentPage(1);
        List<Notice> records = allNoticeByCurrentPage.getRecords();
        if (records.size() > 0) {
            Notice notice = records.get(0);
            return notice;
        } else {
            return null;
        }
    }
}
