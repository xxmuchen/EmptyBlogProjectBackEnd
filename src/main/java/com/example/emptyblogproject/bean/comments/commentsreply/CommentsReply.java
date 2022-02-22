package com.example.emptyblogproject.bean.comments.commentsreply;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: 王程翔
 * Date: 2022/2/22
 * Time: 17:59
 * Description: 子评论记录
 */
@Data
@Deprecated
public class CommentsReply {
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    private Long userId;
    private String userName;
    private String userAvatar;
    private Long BeReviewedUserId;
    private String BeReviewedUserName;
    private String BeReviewedUserAvatar;
    private int likeNum;
    private String content;
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
    private String commentsParentId;
    private String commentsReplyId;
    @TableLogic(value = "0" , delval = "1")
    private boolean del;

    public String getCommentsReplyId() {
        return commentsReplyId;
    }

    public void setCommentsReplyId(String commentsReplyId) {
        this.commentsReplyId = commentsReplyId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCommentsParentId() {
        return commentsParentId;
    }

    public void setCommentsParentId(String commentsParentId) {
        this.commentsParentId = commentsParentId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserAvatar() {
        return userAvatar;
    }

    public void setUserAvatar(String userAvatar) {
        this.userAvatar = userAvatar;
    }

    public Long getBeReviewedUserId() {
        return BeReviewedUserId;
    }

    public void setBeReviewedUserId(Long beReviewedUserId) {
        BeReviewedUserId = beReviewedUserId;
    }

    public String getBeReviewedUserName() {
        return BeReviewedUserName;
    }

    public void setBeReviewedUserName(String beReviewedUserName) {
        BeReviewedUserName = beReviewedUserName;
    }

    public String getBeReviewedUserAvatar() {
        return BeReviewedUserAvatar;
    }

    public void setBeReviewedUserAvatar(String beReviewedUserAvatar) {
        BeReviewedUserAvatar = beReviewedUserAvatar;
    }

    public int getLikeNum() {
        return likeNum;
    }

    public void setLikeNum(int likeNum) {
        this.likeNum = likeNum;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public boolean isDel() {
        return del;
    }

    public void setDel(boolean del) {
        this.del = del;
    }
}
