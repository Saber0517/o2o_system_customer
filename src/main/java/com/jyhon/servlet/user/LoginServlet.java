package com.jyhon.servlet.user;

import com.jyhon.entiy.UserEntity;
import com.jyhon.service.UserEntityService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * Created by ZHANGJA4 on 8/3/2015.
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {
    private UserEntityService userEntityService;

    public UserEntityService getUserEntityService() {
        return userEntityService;
    }

    public void setUserEntityService(UserEntityService userEntityService) {
        this.userEntityService = userEntityService;
        System.out.println(this.userEntityService);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("dopost");
        //请求参数接收
        UserEntity userEntity = getUserEntity(request);
        //将来由IOC完成
//        UserEntityService userEntityService = new UserEntityServiceImpl();
        //类型转换
        //校验
        //数据封装
        UserEntity currentUser = userEntityService.verify(userEntity);

        System.out.println(currentUser);
        if (currentUser != null) {
            forWardSuccessResult(request, response, currentUser);
        } else {
            forWardLogin(request, response);
        }
    }

    private void forWardLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().setAttribute("ErrorMessage", "name or password wrong!");
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }

    private void forWardSuccessResult(HttpServletRequest request, HttpServletResponse response, UserEntity currentUser) throws ServletException, IOException {
        currentUser.setPassword("");
        //传递数据
        request.getSession().setAttribute("currentUser", currentUser);
        //在线状态改变
        //请求转发
        request.getSession().setAttribute("ErrorMessage", null);
        request.getRequestDispatcher("PanelServlet").forward(request, response);
    }

    private UserEntity getUserEntity(HttpServletRequest request) {
        UserEntity userEntity = new UserEntity();
        String name = request.getParameter("name");
        userEntity.setUserName(name);
        String pwd = request.getParameter("psw");
        userEntity.setPassword(pwd);
        return userEntity;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("doget");
        doPost(request, response);
    }

}
