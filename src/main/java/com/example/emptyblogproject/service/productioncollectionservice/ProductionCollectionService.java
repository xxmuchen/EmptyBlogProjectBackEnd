package com.example.emptyblogproject.service.productioncollectionservice;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.emptyblogproject.bean.productioncollection.ProductionCollection;
import com.example.emptyblogproject.bean.productionstar.ProductionStar;

public interface ProductionCollectionService extends IService<ProductionCollection> {

    /*重新收藏日记*/
    public boolean reCollectDiary(Long id);

    /*查询取消收藏的记录*/
    public ProductionCollection getOneHasDelDiaryCollection(Long userId , Long objId , String type);

    /*查询收藏记录*/
    public ProductionCollection getOneDiaryCollection(Long userId , Long objId , String type);

}
