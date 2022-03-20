package com.example.emptyblogproject.controller.griphiccontroller;

import com.example.emptyblogproject.bean.griphic.Griphic;
import com.example.emptyblogproject.bean.user.User;
import com.example.emptyblogproject.service.griphicservice.GriphicService;
import com.example.emptyblogproject.service.userservice.UserService;
import com.example.emptyblogproject.utils.UserTokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: 王程翔
 * Date: 2022/3/7
 * Time: 18:25
 * Description:
 */
@RestController
public class UserSpaceGriphicController {

    @Autowired
    UserTokenUtils userTokenUtils;
    @Autowired
    UserService userService;
    @Autowired
    GriphicService griphicService;

    @GetMapping("/getUserSpaceGriphicOrderCreateTime")
    public List<Griphic> getUserSpaceGriphicOrderByCreateTime(HttpServletRequest httpServletRequest) {
        String authorization = httpServletRequest.getHeader("authorization");
        User user = userTokenUtils.parseTokenAndGetUser(authorization);
        List<Griphic> userSpaceGriphicOrderCreateTime = griphicService.getUserSpaceGriphicOrderCreateTime(user.getId());

        return userSpaceGriphicOrderCreateTime;
    }

    @GetMapping("/getUserSpaceGriphicStateSuccessOrderCreateTime")
    public List<Griphic> getUserSpaceGriphicStateSuccessOrderCreateTime(HttpServletRequest httpServletRequest) {
        String authorization = httpServletRequest.getHeader("authorization");
        User user = userTokenUtils.parseTokenAndGetUser(authorization);
        List<Griphic> userSpaceGriphicOrderCreateTime = griphicService.getUserSpaceGriphicStateSuccessOrderCreateTime(user.getId());

        return userSpaceGriphicOrderCreateTime;
    }

    @GetMapping("/getUserSpaceGriphicStateWaitOrderCreateTime")
    public List<Griphic> getUserSpaceGriphicStateWaitOrderCreateTime(HttpServletRequest httpServletRequest) {
        String authorization = httpServletRequest.getHeader("authorization");
        User user = userTokenUtils.parseTokenAndGetUser(authorization);
        List<Griphic> userSpaceGriphicOrderCreateTime = griphicService.getUserSpaceGriphicStateWaitOrderCreateTime(user.getId());

        return userSpaceGriphicOrderCreateTime;
    }

    @GetMapping("/getUserSpaceGriphicStateFailOrderCreateTime")
    public List<Griphic> getUserSpaceGriphicStateFailOrderCreateTime(HttpServletRequest httpServletRequest) {
        String authorization = httpServletRequest.getHeader("authorization");
        User user = userTokenUtils.parseTokenAndGetUser(authorization);
        List<Griphic> userSpaceGriphicOrderCreateTime = griphicService.getUserSpaceGriphicStateFailOrderCreateTime(user.getId());

        return userSpaceGriphicOrderCreateTime;
    }

    @DeleteMapping("/delUserSpaceGriphicBygriphicId")
    public List<Griphic> delUserSpaceGriphicBygriphicId(@RequestParam("griphicId") Long griphicId , HttpServletRequest httpServletRequest) {
        String authorization = httpServletRequest.getHeader("authorization");
        User user = userTokenUtils.parseTokenAndGetUser(authorization);
        Griphic griphic = griphicService.getById(griphicId);
        if (user == null) {
            throw new RuntimeException("用户不存在");
        }
        if (griphic == null) {
            throw new RuntimeException("图文记不存在");
        }


        if (!griphic.getAuthorId().equals(user.getId())) {
            throw new RuntimeException("数据错误，请重试");
        }
        boolean flag = griphicService.removeById(griphic.getId());
        if (flag) {
            return griphicService.getUserSpaceGriphicOrderCreateTime(user.getId());
        }else {
            throw new RuntimeException("删除错误,请重试");
        }
    }

    /*获取用户点赞的日记  按点赞时间排序*/
    @GetMapping("/getUserSpaceGriphicUserStarOrderByCreateTime")
    public List<Griphic> getUserSpaceGriphicUserStarOrderByCreateTime(HttpServletRequest httpServletRequest) {
        String authorization = httpServletRequest.getHeader("authorization");
        User user = userTokenUtils.parseTokenAndGetUser(authorization);
        List<Griphic> userSpaceGriphicUserStarOrderByCreateTime = griphicService.getUserSpaceGriphicUserStarOrderByCreateTime(user.getId());
        return userSpaceGriphicUserStarOrderByCreateTime;
    }

    /*获取用户收藏的日记  按点赞时间排序*/
    @GetMapping("/getUserSpaceGriphicUserCollectionOrderByCreateTime")
    public List<Griphic> getUserSpaceGriphicUserCollectionOrderByCreateTime(HttpServletRequest httpServletRequest) {
        String authorization = httpServletRequest.getHeader("authorization");
        User user = userTokenUtils.parseTokenAndGetUser(authorization);
        List<Griphic> userSpaceGriphicUserCollectionOrderByCreateTime = griphicService.getUserSpaceGriphicUserCollectionOrderByCreateTime(user.getId());
        return userSpaceGriphicUserCollectionOrderByCreateTime;
    }
}
