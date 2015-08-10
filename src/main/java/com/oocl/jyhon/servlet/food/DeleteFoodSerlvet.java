package com.oocl.jyhon.servlet.food;

import com.oocl.jyhon.entiy.UserEntity;
import com.oocl.jyhon.service.FoodEntityService;
import com.oocl.jyhon.serviceimpl.FoodEntityServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * Created by ZHANGJA4 on 8/7/2015.
 */
@WebServlet(name = "DeleteFoodSerlvet", urlPatterns = "/DeleteFoodSerlvet")
public class DeleteFoodSerlvet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String foodId = request.getParameter("foodId");
        UserEntity userEntity = (UserEntity) request.getSession().getAttribute("currentUser");
        if (null != userEntity && null != foodId) {
            //??service
            FoodEntityService foodEntityService = new FoodEntityServiceImpl();
            Map<String, String> resultMap =foodEntityService.deleteFoodEntity(Integer.valueOf(foodId), userEntity.getUserID());
            for (String key : resultMap.keySet()) {
                request.setAttribute(key, resultMap.get(key));
            }
        }
        request.getRequestDispatcher("FoodSerlvet?typeID=" + foodId).forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
