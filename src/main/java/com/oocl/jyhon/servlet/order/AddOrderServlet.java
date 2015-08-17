package com.oocl.jyhon.servlet.order;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.oocl.jyhon.entiy.FoodTypeEntity;
import com.oocl.jyhon.entiy.OrderEntity;
import com.oocl.jyhon.entiy.UserEntity;
import com.oocl.jyhon.service.OrderService;
import com.oocl.jyhon.serviceimpl.OrderServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by WhiteSaber on 15/8/15.
 */
@WebServlet(name = "AddOrderServlet", urlPatterns = {"/AddOrderServlet"})
public class AddOrderServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String orderJson = request.getParameter("json");

        if (null == orderJson) {
            return;
        }

        OrderEntity orderEntity = getOrderEntity(orderJson);

        List<OrderEntity> orderEntityList = getOrderEntitieList(request);

        UserEntity userEntity = (UserEntity) request.getSession().getAttribute("currentUser");
        if (null == userEntity) {
            return;
        }
        //设置当前order下单用户信息
        orderEntity.setUserID(userEntity.getUserID());
        //插入DB
        final Integer result = InsertOrderIntoDB(orderEntity);
        //发送返回结果
        sendAddOrderResult(request, response, orderEntity, orderEntityList, result);

    }

    private void sendAddOrderResult(HttpServletRequest request, HttpServletResponse response, OrderEntity orderEntity, List<OrderEntity> orderEntityList, Integer result) throws IOException {
        JsonObject object = new JsonObject();
        Gson gson = new Gson();
        if (1 < result) {
            orderEntity.setOrderId(result);
            orderEntityList.add(orderEntity);
            request.getSession().setAttribute("orderEntityList", orderEntityList);
            object.addProperty("insert", "success");
            object.addProperty("orderId", result);
        } else {
            object.addProperty("insert", "fail");
        }
        response.setContentType("application/json");
        response.getOutputStream().write(gson.toJson(object, JsonObject.class).getBytes());
    }

    private Integer InsertOrderIntoDB(OrderEntity orderEntity) {
        OrderService orderService = new OrderServiceImpl();
        return orderService.addOrder(orderEntity);
    }

    private List<OrderEntity> getOrderEntitieList(HttpServletRequest request) {
        List<OrderEntity> orderEntityList = (List<OrderEntity>) request.getSession().getAttribute("orderEntityList");
        if (null == orderEntityList) {
            orderEntityList = new LinkedList<OrderEntity>();
        }
        return orderEntityList;
    }

    private OrderEntity getOrderEntity(String orderJson) {
        Gson gson = new Gson();
        OrderEntity orderEntity = gson.fromJson(orderJson, OrderEntity.class);
        //date 要以serer目前的时间为主
        orderEntity.setDate(new Date());
        //设置为审核状态
        orderEntity.setStatusId(3);
        //for test orderCount set to 1
        orderEntity.setOrderCount(1);
        return orderEntity;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
