package com.jyhon.serviceimpl;

import com.jyhon.dao.OrderEntityDao;
import com.jyhon.entiy.OrderEntity;
import com.jyhon.service.OrderService;

import java.util.List;

/**
 * Created by WhiteSaber on 15/8/15.
 */
public class OrderServiceImpl implements OrderService {
//    OrderEntityDao orderEntityDao = new OrderEntityDaoImpl();

    static OrderEntityDao orderEntityDao;

    public OrderEntityDao getOrderEntityDao() {
        return orderEntityDao;
    }

    public void setOrderEntityDao(OrderEntityDao orderEntityDao) {
        this.orderEntityDao = orderEntityDao;
    }

    public int addOrder(OrderEntity orderEntity) {
        return orderEntityDao.addEntity(orderEntity);
    }

    public List<OrderEntity> findOrderByUserId(Integer userId) {
        return orderEntityDao.findOrderByUserId(userId);
    }
}
