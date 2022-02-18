package com.example.emptyblogproject.bean.homepage;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;

import java.util.List;

@Data
//@TableName("home_page_info")
public class HomePageInfo {
    @TableId
    private int id;
    private String type;
    private String url;
    private List<HomePageInfoMedia> homePageInfoMedia;
    private List<HomePageInfoExample> homePageInfoExample;
    private List<HomePageInfoContent> homePageInfoContent;

    @TableLogic(value = "0" , delval = "1")
    boolean del;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isDel() {
        return del;
    }

    public void setDel(boolean del) {
        this.del = del;
    }

    public List<HomePageInfoMedia> getHomePageInfoMedia() {
        return homePageInfoMedia;
    }

    public void setHomePageInfoMedia(List<HomePageInfoMedia> homePageInfoMedia) {
        this.homePageInfoMedia = homePageInfoMedia;
    }

    public List<HomePageInfoExample> getHomePageInfoExample() {
        return homePageInfoExample;
    }

    public void setHomePageInfoExample(List<HomePageInfoExample> homePageInfoExample) {
        this.homePageInfoExample = homePageInfoExample;
    }

    public List<HomePageInfoContent> getHomePageInfoContent() {
        return homePageInfoContent;
    }

    public void setHomePageInfoContent(List<HomePageInfoContent> homePageInfoContent) {
        this.homePageInfoContent = homePageInfoContent;
    }
}
