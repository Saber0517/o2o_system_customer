package com.oocl.jyhon.serviceimpl;

import com.oocl.jyhon.dao.OrderEntityDao;
import com.oocl.jyhon.daoimple.OrderEntityDaoImpl;
import com.oocl.jyhon.entiy.OrderEntity;
import com.oocl.jyhon.service.OrderService;

import java.util.List;

/**
 * Created by WhiteSaber on 15/8/15.
 */
public class OrderServiceImpl implements OrderService {
    OrderEntityDao orderEntityDao = new OrderEntityDaoImpl();

    public int addOrder(OrderEntity orderEntity) {
        return orderEntityDao.addEntity(orderEntity);
    }

    public List<OrderEntity> findOrderByUserId(Integer userId) {
        return orderEntityDao.findOrderByUserId(userId);
    }
}
