package com.oocl.jyhon.servlet.food;

import com.oocl.jyhon.entiy.FoodEntity;
import com.oocl.jyhon.entiy.UserEntity;
import com.oocl.jyhon.serviceimpl.FoodEntityServiceImpl;
import com.oocl.jyhon.util.FormDataUtil;
import org.apache.commons.fileupload.FileItem;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

/**
 * Created by ZHANGJA4 on 8/7/2015.
 */
@WebServlet(name = "NewFoodServlet", urlPatterns = {"/NewFoodServlet"})

public class NewFoodServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //????
        FormDataUtil formDataUtil = new FormDataUtil();
        String pathTemp = formDataUtil.InitFileFolder(this.getServletContext());
        List<FileItem> items = formDataUtil.getFileItems(request, pathTemp);


        FoodEntity foodEntity = getFoodEntity(items);
        foodEntity.setStatusID(3);
        //??service
        FoodEntityServiceImpl foodEntityServiceImple = new FoodEntityServiceImpl();

        //??????????master
        UserEntity userEntity = (UserEntity) request.getSession().getAttribute("currentUser");
        foodEntity.setUserID(userEntity.getUserID());
        //????
        Map<String, String> resultMap = foodEntityServiceImple.addFoodEntity(foodEntity);

        for (String key : resultMap.keySet()) {
            request.setAttribute(key, resultMap.get(key));
        }
        request.getRequestDispatcher("main/newFood.jsp").forward(request, response);
        return;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    private FoodEntity getFoodEntity(List<FileItem> items) throws UnsupportedEncodingException {
        FoodEntity foodEntity = new FoodEntity();
        for (FileItem item : items) {
            if (item.isFormField()) {
                String fileName = item.getFieldName();
                if (fileName.equals("name")) {
                    foodEntity.setFoodName(item.getString("UTF-8"));
                } else if (fileName.equals("price")) {
                    foodEntity.setPrice(Double.valueOf(item.getString()));
                } else if (fileName.equals("type")) {
                    foodEntity.setTypeID(Integer.valueOf(item.getString()));
                }
                System.out.print(item.getFieldName() + ":");
                System.out.println(item.getString());
            } else {
                String pic = item.getFieldName();
                System.out.println(item.getName());
                foodEntity.setPictureURL(item.getName());
            }
        }
        return foodEntity;
    }


}
