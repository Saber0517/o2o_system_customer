package com.oocl.jyhon.service;

import com.oocl.jyhon.entiy.OrderEntity;

import java.util.List;

/**
 * Created by WhiteSaber on 15/8/15.
 */
public interface OrderService {
    public int addOrder(OrderEntity orderEntity);

    public List<OrderEntity> findOrderByUserId(Integer id);
}
