package com.oocl.jyhon.servlet;

import com.oocl.jyhon.dao.EntityDao;
import com.oocl.jyhon.daoimple.StatusEntityDaoImple;
import com.oocl.jyhon.entiy.FoodTypeEntity;
import com.oocl.jyhon.entiy.StatusEntity;
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
 * Created by ZHANGJA4 on 8/7/2015.
 */
@WebServlet(name = "PanelServlet", urlPatterns = {"/PanelServlet", "/index.jsp"})
public class PanelServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //foodType
        FoodTypeEntityService foodTypeEntityService = new FoodTypeEntityServiceImpl();
        List<FoodTypeEntity> foodTypeEntityList = foodTypeEntityService.findAll();
        request.getSession().setAttribute("foodTypeEntityList", foodTypeEntityList);

        //status
        EntityDao statusEntityDaoImple = new StatusEntityDaoImple();
        List<StatusEntity> statusEntityList = statusEntityDaoImple.findAll();
        request.getSession().setAttribute("statusEntityList", statusEntityList);
        response.sendRedirect("main/index.jsp");
        // request.getRequestDispatcher("main/index.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
