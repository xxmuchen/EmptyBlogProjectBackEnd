package com.example.emptyblogproject.service.productionstarservice;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.emptyblogproject.bean.productionstar.ProductionStar;

public interface ProductionStarService extends IService<ProductionStar> {

    /*取消日记点赞*/
//    public boolean cancelDiaryStar(Long userId , Long diaryId);

    /*重新点赞日记*/
    public boolean reLikeDiary(Long id);

    /*查询取消点赞的记录*/
    public ProductionStar getOneHasDelDiaryStar(Long userId , Long objId , String type);
}
