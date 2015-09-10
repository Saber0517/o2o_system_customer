package com.jyhon.servlet.order;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.jyhon.entiy.FoodEntity;
import com.jyhon.service.FoodEntityService;
import com.jyhon.serviceimpl.FoodEntityServiceImpl;
import com.jyhon.entiy.OrderEntity;
import com.jyhon.entiy.UserEntity;
import com.jyhon.service.OrderService;
import com.jyhon.serviceimpl.OrderServiceImpl;

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
        UserEntity userEntity = (UserEntity) request.getSession().getAttribute("currentUser");

        if (null == userEntity || null == userEntity.getUserID()) {
            return;
        }

        if (null == orderEntityList) {
            orderEntityList = new LinkedList<OrderEntity>();
        }

        response.setContentType("application/json");

        orderEntityList = getOrderEntities(request, orderEntityList, userEntity);
        //获取对应的食物的数据
        FoodEntityService foodEntityService = new FoodEntityServiceImpl();
        List<String> foodIdList = new LinkedList<String>();
        for (OrderEntity orderEntity : orderEntityList) {
            foodIdList.add(String.valueOf(orderEntity.getFoodId()));
        }
        List<FoodEntity> orderFoodList = foodEntityService.searchFoodEntityByFoodId(foodIdList);

        Double allPrice = countAllPirce(orderEntityList, orderFoodList);

        Gson gson = new Gson();
        JsonObject jsonObject = fillJsonObject(response, orderEntityList, orderFoodList, allPrice, gson);
        response.getOutputStream().write(gson.toJson(jsonObject, JsonObject.class).getBytes());

    }

    /**
     * 获取存在购物车中的内容，并设置到session中
     */
    private List<OrderEntity> getOrderEntities(HttpServletRequest request, List<OrderEntity> orderEntityList, UserEntity userEntity) {
        if (null == orderEntityList || 0 == orderEntityList.size()) {
            OrderService orderService = new OrderServiceImpl();
            orderEntityList = orderService.findOrderByUserId(Integer.valueOf(userEntity.getUserID()));
            request.getSession().setAttribute("orderEntityList", orderEntityList);
        }
        return orderEntityList;
    }

    private JsonObject fillJsonObject(HttpServletResponse response, List<OrderEntity> orderEntityList, List<FoodEntity> orderFoodList, Double allPrice, Gson gson) {
        JsonObject jsonObject = new JsonObject();
        //OrderJson
        response.setContentType("application/json");
        String orderEntityListJson = gson.toJson(orderEntityList);
        jsonObject.addProperty("orderEntityList", orderEntityListJson);
        //FoodJson
        String orderFoodListJson = gson.toJson(orderFoodList);
        jsonObject.addProperty("orderFoodList", orderFoodListJson);
        //all price
        jsonObject.addProperty("allPrice", allPrice);
        return jsonObject;
    }

    private Double countAllPirce(List<OrderEntity> orderEntityList, List<FoodEntity> orderFoodList) {
        Double allPrice = new Double(0);
        for (OrderEntity orderEntity : orderEntityList) {
            Integer foodId = orderEntity.getFoodId();
            Integer count = orderEntity.getOrderCount();
            for (FoodEntity foodEntity : orderFoodList) {
                if (foodEntity.getFoodID() == foodId) {
                    allPrice += foodEntity.getPrice() * count;
                }
            }
        }
        return allPrice;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
