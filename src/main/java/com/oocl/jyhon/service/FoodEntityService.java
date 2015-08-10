package com.oocl.jyhon.service;

import com.oocl.jyhon.entiy.FoodEntity;

import java.util.List;
import java.util.Map;

/**
 * Created by ZHANGJA4 on 8/7/2015.
 */
public interface FoodEntityService {
    public Map<String, String> updateFoodEntity(Integer foodId, Double price);

    public Map<String, String> deleteFoodEntity(Integer foodId, Integer userId);

    public Map<String, String> addFoodEntity(FoodEntity foodEntity);

    public List<FoodEntity> findAll();

    public List<FoodEntity> groupByTypeId(Integer typeId);

}

