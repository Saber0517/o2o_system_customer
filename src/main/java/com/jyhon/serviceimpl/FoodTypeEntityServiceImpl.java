package com.jyhon.serviceimpl;

import com.jyhon.daoimple.FoodTypeEntityDaoImple;
import com.jyhon.entiy.FoodTypeEntity;
import com.jyhon.service.FoodTypeEntityService;

import java.util.List;

/**
 * Created by ZHANGJA4 on 8/8/2015.
 */
public class FoodTypeEntityServiceImpl implements FoodTypeEntityService {
    //    FoodTypeEntityDao foodTypeEntityDaoImple = new FoodTypeEntityDaoImple();
    static FoodTypeEntityDaoImple foodTypeEntityDaoImple;

    public FoodTypeEntityDaoImple getFoodTypeEntityDaoImple() {
        return foodTypeEntityDaoImple;
    }

    public void setFoodTypeEntityDaoImple(FoodTypeEntityDaoImple foodTypeEntityDaoImple) {
        this.foodTypeEntityDaoImple = foodTypeEntityDaoImple;
    }

    public List<FoodTypeEntity> findAll() {
        List<FoodTypeEntity> foodTypeEntityList = foodTypeEntityDaoImple.findAll();
        return foodTypeEntityList;
    }
}
