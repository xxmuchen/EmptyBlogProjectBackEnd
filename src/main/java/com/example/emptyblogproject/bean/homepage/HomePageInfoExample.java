package com.example.emptyblogproject.bean.homepage;

import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;

@Data
public class HomePageInfoExample {
    private int id;
    private String content;
    private int homePageInfoId;
    private int did;
    @TableLogic(value = "0" , delval = "1")
    private boolean del;

    public int getDid() {
        return did;
    }

    public void setDid(int did) {
        this.did = did;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getHomePageInfoId() {
        return homePageInfoId;
    }

    public void setHomePageInfoId(int homePageInfoId) {
        this.homePageInfoId = homePageInfoId;
    }

    public boolean isDel() {
        return del;
    }

    public void setDel(boolean del) {
        this.del = del;
    }


}
