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
        String json = request.getParameter("json");

        if (null == json) {
            return;
        }


        List<OrderEntity> orderEntityList = (List<OrderEntity>) request.getSession().getAttribute("orderEntityList");
        if (null == orderEntityList) {
            orderEntityList = new LinkedList<OrderEntity>();
        }


        Gson gson = new Gson();
        OrderEntity orderEntity = gson.fromJson(json, OrderEntity.class);
        //date 要以serer目前的时间为主
        orderEntity.setDate(new Date());
        //设置为审核状态
        orderEntity.setStatusId(3);
        //for test orderCount set to 1
        orderEntity.setOrderCount(1);
        UserEntity userEntity = (UserEntity) request.getSession().getAttribute("currentUser");
        if (null == userEntity) {
            return;
        }
        orderEntity.setUserID(userEntity.getUserID());

        OrderService orderService = new OrderServiceImpl();
        final Integer result = orderService.addOrder(orderEntity);

        //发送返回结果
        JsonObject object = new JsonObject();
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

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
