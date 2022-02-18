package com.example.emptyblogproject.bean.homepage;

import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;

@Data
public class HomePageInfoContent {
    private int id;
    private String content;
    private int homePageInfoId;
    @TableLogic(value = "0" , delval = "1")

    private boolean del;
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
