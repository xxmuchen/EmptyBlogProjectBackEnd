package com.example.emptyblogproject.bean.datavisualization;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: 王程翔
 * Date: 2022/3/15
 * Time: 19:20
 * Description:
 */
@Data
public class DataVisualizationDateAndInteger {
//    private Long id;
//    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date time;
    private int number;

}
