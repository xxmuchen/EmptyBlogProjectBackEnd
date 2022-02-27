package com.example.emptyblogproject.service.vlogservice.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.emptyblogproject.bean.vlog.Vlog;
import com.example.emptyblogproject.mapper.vlogmapper.VlogMapper;
import com.example.emptyblogproject.service.vlogservice.VlogService;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * User: 王程翔
 * Date: 2022/2/27
 * Time: 14:57
 * Description:
 */

@Service
public class VlogServiceImpl extends ServiceImpl<VlogMapper , Vlog> implements VlogService {
}
