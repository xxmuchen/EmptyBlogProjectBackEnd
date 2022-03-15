package com.example.emptyblogproject.controller.vlogcontroller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.emptyblogproject.bean.user.User;
import com.example.emptyblogproject.bean.vlog.Vlog;
import com.example.emptyblogproject.service.userservice.UserService;
import com.example.emptyblogproject.service.vlogservice.VlogService;
import com.example.emptyblogproject.utils.UserTokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created with IntelliJ IDEA.
 * User: 王程翔
 * Date: 2022/3/10
 * Time: 23:09
 * Description:
 */
@RestController
public class UserSpaceVlogController {
    @Autowired
    UserTokenUtils userTokenUtils;
    @Autowired
    UserService userService;
    @Autowired
//    DiaryService diaryService;
    VlogService vlogService;

    @GetMapping("/getUserSpaceVlogOrderCreateTime")
    public Page<Vlog> getUserSpaceVlogOrderCreateTime(@RequestParam("currentIndex") int currentPage , HttpServletRequest httpServletRequest) {
        String authorization = httpServletRequest.getHeader("authorization");
        User user = userTokenUtils.parseTokenAndGetUser(authorization);
        Page<Vlog> userSpaceVlogOrderCreateTime = vlogService.getUserSpaceVlogOrderCreateTime(currentPage, user.getId());
        return userSpaceVlogOrderCreateTime;
    }

    @DeleteMapping("/delUserSpaceVlogByVlogId")
    public Page<Vlog> delUserSpaceVlogByVlogId(@RequestParam("vlogId") Long vlogId , HttpServletRequest httpServletRequest) {
        String authorization = httpServletRequest.getHeader("authorization");
        User user = userTokenUtils.parseTokenAndGetUser(authorization);
        Vlog vlog = vlogService.getById(vlogId);
        if (user == null) {
            throw new RuntimeException("用户不存在");
        }
        if (vlog == null) {
            throw new RuntimeException("Vlog不存在");
        }


        if (!vlog.getAuthorId().equals(user.getId())) {
            throw new RuntimeException("数据错误，请重试");
        }
        boolean flag = vlogService.removeById(vlog.getId());
        if (flag) {
            return vlogService.getUserSpaceVlogOrderCreateTime(1 , user.getId());
        }else {
            throw new RuntimeException("删除错误,请重试");
        }
    }

    /*获取用户点赞的日记  按点赞时间排序*/
    @GetMapping("/getUserSpaceVlogUserStarOrderByCreateTime")
    public Page<Vlog> getUserSpaceVlogUserStarOrderByCreateTime(@RequestParam("currentIndex") int currentPage , HttpServletRequest httpServletRequest) {
        String authorization = httpServletRequest.getHeader("authorization");
        User user = userTokenUtils.parseTokenAndGetUser(authorization);
        Page<Vlog> userSpaceVlogUserStarOrderByCreateTime = vlogService.getUserSpaceVlogUserStarOrderByCreateTime(currentPage, user.getId());
        return userSpaceVlogUserStarOrderByCreateTime;
    }

    /*获取用户收藏的日记  按点赞时间排序*/
    @GetMapping("/getUserSpaceVlogUserCollectionOrderByCreateTime")
    public Page<Vlog> getUserSpaceVlogUserCollectionOrderByCreateTime(@RequestParam("currentIndex") int currentPage , HttpServletRequest httpServletRequest) {
        String authorization = httpServletRequest.getHeader("authorization");
        User user = userTokenUtils.parseTokenAndGetUser(authorization);
        Page<Vlog> userSpaceVlogUserCollectionOrderByCreateTime = vlogService.getUserSpaceVlogUserCollectionOrderByCreateTime(currentPage, user.getId());
        return userSpaceVlogUserCollectionOrderByCreateTime;
    }
}
