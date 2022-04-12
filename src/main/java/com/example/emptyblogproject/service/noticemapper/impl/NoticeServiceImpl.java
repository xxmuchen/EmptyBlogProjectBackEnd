package com.example.emptyblogproject.service.noticemapper.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.emptyblogproject.bean.notice.Notice;
import com.example.emptyblogproject.mapper.noticemapper.NoticeMapper;
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
public class NoticeServiceImpl extends ServiceImpl<NoticeMapper , Notice> implements NoticeService {
    @Override
    public Page<Notice> getAllNoticeByCurrentPage(int currentPage) {
        QueryWrapper<Notice> queryWrapper = new QueryWrapper<>();
        Page<Notice> page = new Page<>(currentPage , 9);
        queryWrapper.orderByDesc("create_time");
        Page<Notice> noticePage = this.page(page , queryWrapper);
        return noticePage;
    }
}
