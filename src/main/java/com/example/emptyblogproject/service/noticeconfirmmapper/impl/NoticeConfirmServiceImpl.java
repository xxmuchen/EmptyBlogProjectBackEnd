package com.example.emptyblogproject.service.noticeconfirmmapper.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.emptyblogproject.bean.notice.Notice;
import com.example.emptyblogproject.bean.notice.NoticeConfirm;
import com.example.emptyblogproject.mapper.noticeconfirmmapper.NoticeConfirmMapper;
import com.example.emptyblogproject.mapper.noticemapper.NoticeMapper;
import com.example.emptyblogproject.service.noticeconfirmmapper.NoticeConfirmService;
import com.example.emptyblogproject.service.noticemapper.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: 王程翔
 * Date: 2022/4/10
 * Time: 8:32
 * Description:
 */
@Service
public class NoticeConfirmServiceImpl extends ServiceImpl<NoticeConfirmMapper, NoticeConfirm> implements NoticeConfirmService {

    @Autowired
    NoticeService noticeService;

    @Override
    public boolean getNoticeConfirmInfo(Long userId) {
        Page<Notice> allNoticeByCurrentPage = noticeService.getAllNoticeByCurrentPage(1);
        List<Notice> records = allNoticeByCurrentPage.getRecords();
        Notice notice = records.get(0);
        QueryWrapper<NoticeConfirm> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id" , userId);
        queryWrapper.eq("notice_id" , notice.getId());
        NoticeConfirm noticeConfirm = this.getOne(queryWrapper);
        if (noticeConfirm != null) {
            return true;
        } else {
            return false;
        }
    }
}
