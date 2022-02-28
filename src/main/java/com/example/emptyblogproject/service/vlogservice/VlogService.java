package com.example.emptyblogproject.service.vlogservice;

import com.baomidou.mybatisplus.extension.service.IService;
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
}
