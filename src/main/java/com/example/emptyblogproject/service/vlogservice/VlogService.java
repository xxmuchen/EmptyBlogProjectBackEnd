package com.example.emptyblogproject.service.vlogservice;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.emptyblogproject.bean.dairy.Diary;
import com.example.emptyblogproject.bean.sentence.Sentence;
import com.example.emptyblogproject.bean.vlog.Vlog;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: 王程翔
 * Date: 2022/2/27
 * Time: 14:54
 * Description:
 */
public interface VlogService extends IService<Vlog> {

    /*获取所有可见的视频*/
    public List<Vlog> getAllVlogBySee();

    public Page<Vlog> getUserSpaceVlogOrderCreateTime(int currentPage, Long userId);

    public Page<Vlog> getUserSpaceVlogUserStarOrderByCreateTime(int currentPage, Long userId);
    public Page<Vlog> getUserSpaceVlogUserCollectionOrderByCreateTime(int currentPage, Long userId);
    /*管理员获取所有句子*/
    public Page<Vlog> adminGetAllVlogByPageAndCreateTime(int currentPage);
}
