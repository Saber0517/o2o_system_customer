package com.oocl.jyhon.servlet;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.oocl.jyhon.dao.EntityDao;
import com.oocl.jyhon.daoimple.StatusEntityDaoImple;
import com.oocl.jyhon.entiy.StatusEntity;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by WhiteSaber on 15/8/14.
 */
@WebServlet(name = "StatusServlet",urlPatterns = {"/StatusServlet"})
public class StatusServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<StatusEntity> statusEntityList = (List<StatusEntity>) request.getSession().getAttribute("statusEntityList");

        if (null == statusEntityList) {
            EntityDao statusEntityDaoImple = new StatusEntityDaoImple();
            statusEntityList = statusEntityDaoImple.findAll();
            request.getSession().setAttribute("statusEntityList", statusEntityList);
        }
        Gson gson = new Gson();
        JsonObject jsonObject = new JsonObject();
        //??json??
        response.setContentType("application/json");
        String statusTypeJsonObject = gson.toJson(statusEntityList);
        jsonObject.addProperty("statusMap", statusTypeJsonObject);
        response.getOutputStream().write(gson.toJson(jsonObject, JsonObject.class).getBytes());


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
