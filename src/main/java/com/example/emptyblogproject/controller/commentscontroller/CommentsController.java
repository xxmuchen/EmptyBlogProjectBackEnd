package com.example.emptyblogproject.controller.commentscontroller;

import com.alibaba.fastjson.JSON;
import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.example.emptyblogproject.annotation.UserLoginToken;
import com.example.emptyblogproject.bean.comments.commentsreply.CommentsReply;
import com.example.emptyblogproject.bean.comments.commentsroot.CommentsRoot;
import com.example.emptyblogproject.bean.user.User;
import com.example.emptyblogproject.service.commentsservice.commentsreplyservice.CommentsReplyService;
import com.example.emptyblogproject.service.commentsservice.commentsrootservice.CommentsRootService;
import com.example.emptyblogproject.service.user.UserService;
import com.example.emptyblogproject.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: 王程翔
 * Date: 2022/2/23
 * Time: 0:39
 * Description: 评论的控制类
 */
@RestController
@Deprecated
public class CommentsController {

    @Autowired
    TokenUtils tokenUtils;

    @Autowired
    UserService userService;

    @Autowired
    CommentsRootService commentsRootService;

    @Autowired
    CommentsReplyService commentsReplyService;

    /*添加父评论，直接对标文章等资源*/
    /*需要从请求参数中获取Authorization、type、content*/
    @UserLoginToken
    @PostMapping("/addRootComments")
    public CommentsRoot addRootComments(@RequestBody CommentsRoot commentsRoot , HttpServletRequest httpServletRequest) {

        String authorization = httpServletRequest.getHeader("Authorization");
        User user = tokenUtils.parseTokenAndGetUser(authorization);

        if (user == null) {
            throw new RuntimeException("用户不存在，请退出重新登陆");
        }

        System.out.println(commentsRoot);

        if (commentsRoot == null && commentsRoot.getContent().length() == 0) {
            throw new RuntimeException("评论失败，请重试");
        }

        CommentsRoot commentsRootResult = commentsRootService.addCommentsRoot(commentsRoot, user);

        if (commentsRootResult != null) {
            return commentsRootResult;
        }else {
            throw new RuntimeException("评论失败，请重试");
        }
    }

    /*添加子评论，对应父评论*/
    /*需要从请求参数中获取Authorization、comments_parent_id、content*/
    @UserLoginToken
    @PostMapping("addReplyComments")
    public CommentsReply addReplyComments(@RequestBody CommentsReply commentsReply , HttpServletRequest httpServletRequest) {
        String authorization = httpServletRequest.getHeader("Authorization");
        User user = tokenUtils.parseTokenAndGetUser(authorization);
        if (user == null) {
            throw new RuntimeException("用户不存在，请退出重新登陆");
        }

        System.out.println(commentsReply);

        if (commentsReply == null && commentsReply.getContent().length() == 0) {
            throw new RuntimeException("评论失败，请重试");
        }

         CommentsReply commentsReplyResult = commentsReplyService.addCommentsReply(commentsReply , user);

        if (commentsReplyResult != null) {
            return commentsReplyResult;
        }else {
            throw new RuntimeException("评论失败，请重试");
        }

    }

    /*根据资源Id获取该资源的所有评论*/
    @GetMapping("getALlRootCommentsByObjId")
    public JSON getALlRootCommentsByObjId(@RequestParam(name = "objId") String objId){
        long obj_id = Long.parseLong(objId);
        List<CommentsRoot> commentsRootList = commentsRootService.getALlRootCommentsByObjId(obj_id);

        for (CommentsRoot commentsRoot: commentsRootList) {

        }

        return null;

//        if (commentsRootList != null) {
//            return commentsRootList;
//        }else {
//            throw new RuntimeException("请刷新重试");
//        }
    }


}
