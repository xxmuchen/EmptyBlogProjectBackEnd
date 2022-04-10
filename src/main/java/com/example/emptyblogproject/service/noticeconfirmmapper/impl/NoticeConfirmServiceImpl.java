package com.example.emptyblogproject.service.noticeconfirmmapper.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.emptyblogproject.bean.notice.Notice;
import com.example.emptyblogproject.bean.notice.NoticeConfirm;
import com.example.emptyblogproject.mapper.noticeconfirmmapper.NoticeConfirmMapper;
import com.example.emptyblogproject.mapper.noticemapper.NoticeMapper;
import com.example.emptyblogproject.service.noticeconfirmmapper.NoticeConfirmService;
import com.example.emptyblogproject.service.noticemapper.NoticeService;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * User: 王程翔
 * Date: 2022/4/10
 * Time: 8:32
 * Description:
 */
@Service
public class NoticeConfirmServiceImpl extends ServiceImpl<NoticeConfirmMapper, NoticeConfirm> implements NoticeConfirmService {
}
