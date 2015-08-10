package com.oocl.jyhon.servlet.food;

import com.oocl.jyhon.entiy.FoodEntity;
import com.oocl.jyhon.entiy.FoodTypeEntity;
import com.oocl.jyhon.service.FoodEntityService;
import com.oocl.jyhon.service.FoodTypeEntityService;
import com.oocl.jyhon.serviceimpl.FoodEntityServiceImpl;
import com.oocl.jyhon.serviceimpl.FoodTypeEntityServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by ZHANGJA4 on 8/6/2015.
 */
@WebServlet(name = "FoodSerlvet", urlPatterns = {"/FoodSerlvet"})
public class FoodSerlvet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //food
        String typeID = request.getParameter("typeID");

        List<FoodEntity> foodEntityList = Collections.emptyList();
        foodEntityList = getFoodEntities(typeID);
        //Set current TypeName
        setCurrentTypeName(request, typeID);
        //result
        SetResultToSession(request, foodEntityList);
        //foward
        if (null != typeID) {
            request.setAttribute("currentFoodTypeId", typeID);
        }

        request.getRequestDispatcher("main/Food.jsp").forward(request, response);
        return;

    }

    private List<FoodEntity> getFoodEntities(String foodId) {
        List<FoodEntity> foodEntityList;
        FoodEntityService foodEntityServiceImple = new FoodEntityServiceImpl();
        if (null != foodId) {
            foodEntityList = foodEntityServiceImple.groupByTypeId(Integer.valueOf(foodId));
        } else {
            foodEntityList = foodEntityServiceImple.findAll();
        }
        return foodEntityList;
    }

    private void setCurrentTypeName(HttpServletRequest request, String foodId) {

        FoodTypeEntityService foodTypeEntityService = new FoodTypeEntityServiceImpl();
        List<FoodTypeEntity> foodTypeEntityList = foodTypeEntityService.findAll();
        for (FoodTypeEntity foodType : foodTypeEntityList) {
            if (foodType.getFoodTypeID().equals(Integer.valueOf(foodId))) {
                request.getSession().setAttribute("currentFoodTypeName", foodType.getFoodTypeName());
            }
        }
    }

    private void SetResultToSession(HttpServletRequest request, List<FoodEntity> foodEntityList) {
        List<FoodEntity> resultList = new LinkedList<FoodEntity>();
        String requestUrl = request.getRequestURI();
        resultList = foodEntityList;
        request.setAttribute("resultList", resultList);
        System.out.println(requestUrl);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
