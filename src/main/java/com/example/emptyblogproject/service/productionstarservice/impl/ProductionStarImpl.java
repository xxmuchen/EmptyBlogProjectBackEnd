package com.example.emptyblogproject.service.productionstarservice.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.emptyblogproject.bean.productionstar.ProductionStar;
import com.example.emptyblogproject.mapper.productionstarmapper.ProductionStarMapper;
import com.example.emptyblogproject.service.productionstarservice.ProductionStarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductionStarImpl extends ServiceImpl<ProductionStarMapper, ProductionStar> implements ProductionStarService {

    @Autowired
    ProductionStarMapper productionStarMapper;

//    @Override
//    public boolean cancelDiaryStar(Long userId, Long diaryId) {
//        boolean flag = diaryStarMapper.cancelDiaryStar(userId, diaryId);
//        return flag;
//    }

    @Override
    public boolean reLikeDiary(Long id) {
        boolean flag = productionStarMapper.reLikeDiary(id);

        return flag;
    }

    @Override
    public ProductionStar getOneHasDelDiaryStar(Long userId , Long objId , String type) {
        ProductionStar productionStar = productionStarMapper.getOneHasDelDiaryStar(userId, objId , type);
        return productionStar;
    }

    @Override
    public ProductionStar getOneDiaryStar(Long userId, Long objId, String type) {
        QueryWrapper<ProductionStar> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id" , userId);
        queryWrapper.eq("obj_id" , objId);
        queryWrapper.eq("type" , type);
        ProductionStar productionStar = this.getOne(queryWrapper);
        return productionStar;
    }

    /*查询取消点赞的记录*/

}
