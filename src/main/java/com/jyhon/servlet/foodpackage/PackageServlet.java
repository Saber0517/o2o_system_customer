package com.jyhon.servlet.foodpackage;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by ZHANGJA4 on 8/7/2015.
 */
@WebServlet(name = "PackageServlet", urlPatterns = "/PackageServlet")
public class PackageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*
        //???IOC??
        EntityDao packageEntityDaoImple = new PackageEntityDaoImple();
        //????
        List<PackageEntity> packageEntityList = packageEntityDaoImple.findAll();
        //????
        request.getSession().setAttribute("packageEntityList", packageEntityList);

        //????
        */
        response.sendRedirect("main/package.jsp");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
