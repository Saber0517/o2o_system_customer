package com.oocl.jyhon.service;

import com.oocl.jyhon.entiy.FoodEntity;

import java.util.List;
import java.util.Map;

/**
 * Created by ZHANGJA4 on 8/7/2015.
 */
public interface FoodEntityService {
    Map<String, String> updateFoodEntity(Integer foodId, Double price);

    Map<String, String> deleteFoodEntity(Integer foodId, Integer userId);

    Map<String, String> addFoodEntity(FoodEntity foodEntity);

    List<FoodEntity> findAll();

    List<FoodEntity> groupByTypeId(Integer typeId);

    List<FoodEntity> searchFoodEntityByFoodId(List<String> foodIdList);

}

