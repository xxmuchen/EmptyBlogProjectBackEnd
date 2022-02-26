package com.example.emptyblogproject.service.productioncollectionservice.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.emptyblogproject.bean.productioncollection.ProductionCollection;
import com.example.emptyblogproject.bean.productionstar.ProductionStar;
import com.example.emptyblogproject.mapper.productioncollectionmapper.ProductionCollectionMapper;
import com.example.emptyblogproject.service.productioncollectionservice.ProductionCollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductionCollectionServiceImpl extends ServiceImpl<ProductionCollectionMapper, ProductionCollection> implements ProductionCollectionService {

//    @Autowired
//    ProductionCollectionMapper productionCollectionMapper;

    @Override
    public boolean reCollectDiary(Long id) {
        boolean flag = baseMapper.reCollectDiary(id);
        return flag;
    }

    @Override
    public ProductionCollection getOneHasDelDiaryCollection(Long userId , Long objId , String type) {
        ProductionCollection productionCollection = baseMapper.getOneHasDelDiaryCollection(userId, objId , type);
        return productionCollection;
    }

    @Override
    public ProductionCollection getOneDiaryCollection(Long userId, Long objId, String type) {
        QueryWrapper<ProductionCollection> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id" , userId);
        queryWrapper.eq("obj_id" , objId);
        queryWrapper.eq("type" , type);
        ProductionCollection productionCollection = this.getOne(queryWrapper);
        return productionCollection;
    }
}
