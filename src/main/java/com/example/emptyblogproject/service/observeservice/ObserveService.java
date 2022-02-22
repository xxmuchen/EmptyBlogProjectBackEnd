package com.example.emptyblogproject.service.observeservice;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.emptyblogproject.bean.observe.Observe;
import com.example.emptyblogproject.bean.observe.observeBo.ObserveNodeBO;
import com.example.emptyblogproject.bean.observe.observeBo.ObserveUserBo;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: 王程翔
 * Date: 2022/2/23
 * Time: 3:05
 * Description: 评论的service
 */

public interface ObserveService extends IService<Observe> {

    List<ObserveNodeBO> queryFirstObserveList (String type , Long objId);


    List<ObserveNodeBO> querySecondObserveList (String type , Long objId);

    /**
     * 功能描述：根据博客id，查询此博客的所有评论信息
     * @param
     * @return 博客的评论信息
     */
    List<ObserveNodeBO> queryObserveByObjId(String type , Long objId);

    /**
     * 功能描述：根据评论id查询用户信息
     * @param observeId 评论id
     * @return 评论信息，携带用户信息
     */
    public ObserveUserBo queryObserveUserById(Long observeId);
}
