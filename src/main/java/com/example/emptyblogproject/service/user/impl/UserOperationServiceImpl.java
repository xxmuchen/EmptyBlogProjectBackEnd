package com.example.emptyblogproject.service.user.impl;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.emptyblogproject.bean.user.UserOperation;
import com.example.emptyblogproject.mapper.UserMapper.UserOperationMapper;
import com.example.emptyblogproject.service.user.UserOperationService;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * User: 王程翔
 * Date: 2022/3/15
 * Time: 2:16
 * Description:
 */
@Service
public class UserOperationServiceImpl extends ServiceImpl<UserOperationMapper , UserOperation> implements UserOperationService {

}
