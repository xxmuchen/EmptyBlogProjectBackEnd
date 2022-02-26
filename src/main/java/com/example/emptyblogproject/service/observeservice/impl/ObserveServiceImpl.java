package com.example.emptyblogproject.service.observeservice.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.emptyblogproject.bean.observe.Observe;
import com.example.emptyblogproject.bean.observe.observeBo.ObserveNodeBO;
import com.example.emptyblogproject.bean.observe.observeBo.ObserveUserBo;
import com.example.emptyblogproject.bean.user.User;
import com.example.emptyblogproject.mapper.observemapper.ObserveMapper;
import com.example.emptyblogproject.service.observeservice.ObserveService;
import com.example.emptyblogproject.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: 王程翔
 * Date: 2022/2/23
 * Time: 3:08
 * Description:
 */
@Service
public class ObserveServiceImpl extends ServiceImpl<ObserveMapper , Observe> implements ObserveService {

//    @Autowired
//    private ObserveMapper observeMapper;

//    @Autowired
//    private UserMapper userMapper;

//    @Autowired
//    private BlogMapper blogMapper;

    @Autowired
    private UserService userService;

    @Override
    public List<ObserveNodeBO> queryFirstObserveList(String type, Long objId) {
        List<ObserveNodeBO> observeNodeBOList = baseMapper.queryFirstObserveList(type, objId);
        return observeNodeBOList;
    }

    @Override
    public List<ObserveNodeBO> querySecondObserveList(String type, Long objId) {
        List<ObserveNodeBO> observeNodeBOList = baseMapper.querySecondObserveList(type, objId);
        return observeNodeBOList;
    }

    @Override
    public List<ObserveNodeBO> queryObserveByObjId(String type, Long objId) {
        //所有未处理的一级评论集合
        List<ObserveNodeBO> firstObserveList = this.queryFirstObserveList(type , objId);
        //所有未处理的二级评论集合
        List<ObserveNodeBO> secondObserveList = this.querySecondObserveList(type , objId);

        //将二级评论用链表的方式添加到一级评论
        List<ObserveNodeBO> list = addAllNode(firstObserveList, secondObserveList);

        //控制台打印评论回复
//        show(list);

        //返回处理后的评论信息
        return list;
    }

    @Override
    public ObserveUserBo queryObserveUserById(Long observeId) {
        Observe observe = this.getById(observeId);
        User user = userService.getById(observe.getObserverId());
        ObserveUserBo observeUserBo = new ObserveUserBo();
        observeUserBo.setObserve(observe);
        observeUserBo.setUser(user);
        return observeUserBo;
    }

    /**
     * 功能描述：将单个node添加到链表中
     *
     * @param firstList   第一层评论集合（链表）
     * @param observeNode 非第一层评论的回复信息
     * @return 是否添加
     */
    private boolean addNode ( List<ObserveNodeBO> firstList, ObserveNodeBO observeNode ) {
        //循环添加
        for (ObserveNodeBO node : firstList) {
            //判断留言的上一段是否是这条留言（判断这条回复，是否是当前评论的回复）
            if (node.getId().equals(observeNode.getLastId())) {
                //是，添加，返回true
                node.getNextNodes().add(observeNode);
                return true;
            } else {
                //否则递归继续判断
                if (node.getNextNodes().size() != 0) {
                    if (addNode(node.getNextNodes(), observeNode)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * 功能描述：将查出来的lastId不为null的回复都添加到第一层Node集合中
     *
     * @param firstList 第一层评论集合（链表）
     * @param thenList  非第一层评论集合（链表）
     * @return 所有评论集合（非第一层评论集合对应添加到第一层评论集合，返回）
     */

    private List<ObserveNodeBO> addAllNode ( List<ObserveNodeBO> firstList, List<ObserveNodeBO> thenList ) {
        while (thenList.size() != 0) {
            int size = thenList.size();
            for (int i = 0; i < size; i++) {
                if (addNode(firstList, new ObserveNodeBO(thenList.get(i)))) {
                    thenList.remove(i);
                    i--;
                    size--;
                }
            }
        }
        return firstList;
    }

    /**
     * 功能描述：打印评论的链表回复信息
     *
     * @param list 评论信息（链表集合）
     */
    private void show (List<ObserveNodeBO> list ) {
        for (ObserveNodeBO node : list) {
            System.out.println(node.getObserverId() + " 用户回复了" + node.getLastId() + "：" + node.getObserveContent());
            //递归打印回复信息
            if (node.getNextNodes().size() != 0) {
                show(node.getNextNodes());
            }
        }
    }

}
