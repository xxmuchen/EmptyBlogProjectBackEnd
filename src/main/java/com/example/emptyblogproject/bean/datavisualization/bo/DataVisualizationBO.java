package com.example.emptyblogproject.bean.datavisualization.bo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: 王程翔
 * Date: 2022/3/15
 * Time: 20:26
 * Description:
 */
@Data
public class DataVisualizationBO {
//    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    List<String> xAxis = new ArrayList<>();
    List<Integer> yAxis = new ArrayList<>();
}
