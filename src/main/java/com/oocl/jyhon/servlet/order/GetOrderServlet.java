package com.oocl.jyhon.servlet.order;

import com.google.gson.Gson;
import com.oocl.jyhon.entiy.OrderEntity;

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
        if (null == orderEntityList) {
            orderEntityList = new LinkedList<OrderEntity>();
        }
        Gson gson = new Gson();

        response.setContentType("application/json");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
