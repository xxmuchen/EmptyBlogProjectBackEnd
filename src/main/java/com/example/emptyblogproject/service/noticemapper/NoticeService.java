package com.example.emptyblogproject.service.noticemapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.emptyblogproject.bean.notice.Notice;

/**
 * Created with IntelliJ IDEA.
 * User: 王程翔
 * Date: 2022/4/10
 * Time: 8:29
 * Description:
 */
public interface NoticeService extends IService<Notice> {

    public Page<Notice> getAllNoticeByCurrentPage(int currentPage);
}
