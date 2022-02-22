package com.example.emptyblogproject.controller.observecontroller;

import com.example.emptyblogproject.bean.observe.observeBo.ObserveNodeBO;
import com.example.emptyblogproject.bean.observe.observeBo.ObserveUserBo;
import com.example.emptyblogproject.service.observeservice.ObserveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: 王程翔
 * Date: 2022/2/23
 * Time: 3:49
 * Description: 评论controller
 */
@RestController
public class ObserveController  {
//    @Autowired
//    private ObserveService observeService;
//    /**
//     * 功能描述：根据博客id，查询此博客的所有评论信息（链表类型的数据）
//     * @param blogId 博客id
//     * @return 博客的评论信息
//     */
//    @GetMapping("/{blogId}")
//    public ResponseEntity<List<ObserveNodeBO>> queryObserveByBlogId (@PathVariable Long blogId)
//    {
//        return ResponseEntity.ok(observeService.queryObserveByBlogId(blogId));
//    }
//
//    /**
//     * 功能描述：根据评论id查询用户信息（评论信息，携带用户信息）
//     * @param observeId 评论id
//     * @return 评论信息，携带用户信息
//     */
//    @GetMapping("/user/{observeId}")
//    public ResponseEntity<ObserveUserBo> queryObserveUserById (
//            @ApiParam(name = "observeId", value = "评论id", required = true)@PathVariable Long observeId
//    ) {
//        return ResponseEntity.ok(observeService.queryObserveUserById(observeId));
//    }
}
