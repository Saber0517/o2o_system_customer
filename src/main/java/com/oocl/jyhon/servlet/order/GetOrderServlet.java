package com.oocl.jyhon.servlet.order;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.oocl.jyhon.entiy.FoodEntity;
import com.oocl.jyhon.entiy.OrderEntity;
import com.oocl.jyhon.service.FoodEntityService;
import com.oocl.jyhon.service.OrderService;
import com.oocl.jyhon.serviceimpl.FoodEntityServiceImpl;
import com.oocl.jyhon.serviceimpl.OrderServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by WhiteSaber on 15/8/15.
 */
@WebServlet(name = "GetOrderServlet", urlPatterns = {"/GetOrderServlet"})
public class GetOrderServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<OrderEntity> orderEntityList = (List<OrderEntity>) request.getSession().getAttribute("orderEntityList");

        String userId = request.getParameter("userID");
        if (null == userId) {
            return;
        }

        if (null == orderEntityList) {
            orderEntityList = new LinkedList<OrderEntity>();
        }

        response.setContentType("application/json");

        //获取存在购物车中的内容
        if (null == orderEntityList || 0 == orderEntityList.size()) {
            OrderService orderService = new OrderServiceImpl();
            orderEntityList = orderService.findOrderByUserId(Integer.valueOf(userId));
            request.getSession().setAttribute("orderEntityList", orderEntityList);
        }
        //获取对应的食物的数据
        FoodEntityService foodEntityService = new FoodEntityServiceImpl();
        List<String> foodIdList = new LinkedList<String>();
        for (OrderEntity orderEntity : orderEntityList) {
            foodIdList.add(String.valueOf(orderEntity.getFoodId()));
        }
        List<FoodEntity> orderFoodList = foodEntityService.searchFoodEntityByFoodId(foodIdList);

        Gson gson = new Gson();
        JsonObject jsonObject = new JsonObject();
        //??json??
        response.setContentType("application/json");
        String orderEntityListJson = gson.toJson(orderEntityList);
        jsonObject.addProperty("orderEntityList", orderEntityListJson);
        response.getOutputStream().write(gson.toJson(jsonObject, JsonObject.class).getBytes());

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
