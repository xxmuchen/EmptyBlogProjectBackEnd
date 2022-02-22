package com.example.emptyblogproject.service.productioncollectionservice.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.emptyblogproject.bean.productioncollection.ProductionCollection;
import com.example.emptyblogproject.mapper.productioncollectionmapper.ProductionCollectionMapper;
import com.example.emptyblogproject.service.productioncollectionservice.ProductionCollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductionCollectionServiceImpl extends ServiceImpl<ProductionCollectionMapper, ProductionCollection> implements ProductionCollectionService {

    @Autowired
    ProductionCollectionMapper productionCollectionMapper;

    @Override
    public boolean reCollectDiary(Long id) {
        boolean flag = productionCollectionMapper.reCollectDiary(id);
        return flag;
    }

    @Override
    public ProductionCollection getOneHasDelDiaryCollection(Long userId , Long objId , String type) {
        ProductionCollection productionCollection = productionCollectionMapper.getOneHasDelDiaryCollection(userId, objId , type);
        return productionCollection;
    }
}
