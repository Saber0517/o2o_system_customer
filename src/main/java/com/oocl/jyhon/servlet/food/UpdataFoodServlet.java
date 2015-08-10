package com.oocl.jyhon.servlet.food;

import com.oocl.jyhon.entiy.FoodEntity;
import com.oocl.jyhon.service.FoodEntityService;
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
@WebServlet(name = "UpdataFoodServlet", urlPatterns = "/UpdataFoodServlet")
public class UpdataFoodServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println();
//        FoodEntity
        Boolean updateFlag = Boolean.valueOf(request.getParameter("update"));
        if (updateFlag) {
            FormDataUtil formDataUtil = new FormDataUtil();
            String pathTemp = formDataUtil.InitFileFolder(this.getServletContext());
            List<FileItem> items = formDataUtil.getFileItems(request, pathTemp);
            FoodEntity foodEntity = getFoodEntity(items);

            //??service
            FoodEntityService foodEntityService = new FoodEntityServiceImpl();
            Map<String, String> resultMap = foodEntityService.updateFoodEntity(foodEntity.getFoodID(), foodEntity.getPrice());

            for (String key : resultMap.keySet()) {
                request.setAttribute(key, resultMap.get(key));
            }
            request.getSession().setAttribute("price", foodEntity.getPrice());
        } else {
            Double price = Double.valueOf(request.getParameter("price"));
            Integer foodId = Integer.valueOf(request.getParameter("foodId"));
            request.getSession().setAttribute("price", price);
            request.getSession().setAttribute("foodId", foodId);
        }

        request.getRequestDispatcher("main/updateFood.jsp").forward(request, response);
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
                } else if (fileName.equals("id")) {
                    foodEntity.setFoodID(Integer.valueOf(item.getString("UTF-8")));
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
