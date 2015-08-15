package com.oocl.jyhon.servlet;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.oocl.jyhon.entiy.FoodTypeEntity;
import com.oocl.jyhon.service.FoodTypeEntityService;
import com.oocl.jyhon.serviceimpl.FoodTypeEntityServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by ZHANGJA4 on 8/14/2015.
 */
@WebServlet(name = "FoodTypeServlet", urlPatterns = {"/FoodTypeServlet"})
public class FoodTypeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<FoodTypeEntity> foodTypeEntityList = (List<FoodTypeEntity>) request.getSession().getAttribute("foodTypeEntityList");
        //foodType
        if (null == request.getSession().getAttribute("foodTypeEntityList")) {
            FoodTypeEntityService foodTypeEntityService = new FoodTypeEntityServiceImpl();
            foodTypeEntityList = foodTypeEntityService.findAll();
            request.getSession().setAttribute("foodTypeEntityList", foodTypeEntityList);
        }
        Gson gson = new Gson();
        JsonObject jsonObject = new JsonObject();
        //??json??
        response.setContentType("application/json");
        String foodTypeJsonObject = gson.toJson(foodTypeEntityList);
        jsonObject.addProperty("foodTypeMap", foodTypeJsonObject);
        response.getOutputStream().write(gson.toJson(jsonObject, JsonObject.class).getBytes());

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
