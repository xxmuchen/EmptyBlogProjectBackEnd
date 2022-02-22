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

}
