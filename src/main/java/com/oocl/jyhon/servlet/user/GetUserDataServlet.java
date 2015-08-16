package com.oocl.jyhon.servlet.user;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.oocl.jyhon.entiy.UserEntity;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by WhiteSaber on 15/8/16.
 */
@WebServlet(name = "GetUserDataServlet", urlPatterns = {"/GetUserDataServlet"})
public class GetUserDataServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserEntity userEntity = (UserEntity) request.getSession().getAttribute("currentUser");
        if (null == userEntity) {
            request.getRequestDispatcher("login.jsp").forward(request, response);
        } else {
            Gson gson = new Gson();
            String userJson = gson.toJson(userEntity, UserEntity.class);
            response.setContentType("application/json");
            response.getOutputStream().write(userJson.getBytes());
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
