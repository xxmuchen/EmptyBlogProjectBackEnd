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


}
