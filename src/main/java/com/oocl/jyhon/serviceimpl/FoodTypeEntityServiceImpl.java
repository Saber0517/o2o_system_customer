package com.oocl.jyhon.serviceimpl;

import com.oocl.jyhon.dao.EntityDao;
import com.oocl.jyhon.dao.FoodTypeEntityDao;
import com.oocl.jyhon.daoimple.FoodTypeEntityDaoImple;
import com.oocl.jyhon.entiy.FoodTypeEntity;
import com.oocl.jyhon.service.FoodTypeEntityService;

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
