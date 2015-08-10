package com.oocl.jyhon.servlet.user;


import com.oocl.jyhon.entiy.UserEntity;
import com.oocl.jyhon.service.UserEntityService;
import com.oocl.jyhon.serviceimpl.UserEntityServiceImpl;
import com.oocl.jyhon.util.VerifyUtil;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by ZHANGJA4 on 8/3/2015.
 */
@WebServlet(name = "SignInServlet", urlPatterns = {"/SignInServlet"})
public class SignInServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //请求参数接收
        request.setCharacterEncoding("UTF-8");
        //将来由IOC完成
        UserEntityService userEntityService = new UserEntityServiceImpl();


        //获得表格参数
        String pathTemp = InitFileFolder();
        List<FileItem> items = getFileItems(request, pathTemp);
        UserEntity userEntity = getUserEntity(items);

        //验证插入信息
        VerifyUtil verifyUtil = new VerifyUtil();
        if (verifySignInData(request, response, userEntity, verifyUtil)) return;

        //插入数据
        Integer dbResult = userEntityService.addEntity(userEntity);
        //错误提示
        if (dbResult <= 0) {
            request.setAttribute("ErrorMessage", "DataBase Insert fail!");
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }
        //成功
        request.setAttribute("ReminderMessage", "Register Success please login");
        request.getRequestDispatcher("login.jsp").forward(request, response);


    }

    private boolean verifySignInData(HttpServletRequest request, HttpServletResponse response, UserEntity userEntity, VerifyUtil verifyUtil) throws ServletException, IOException {
        Map<String, String> verifyParameter = verifyUtil.verifyParameter(userEntity);
        if (!verifyParameter.isEmpty()) {
            //错误提示
            request.setAttribute("verifyParameter", verifyParameter);
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return true;
        }
        return false;
    }

    private String InitFileFolder() {
        String pathTemp = this.getServletContext().getRealPath("\\temp");
        System.out.println(pathTemp);
        String pathUpload = this.getServletContext().getRealPath("/upload");
        System.out.println(pathUpload);
        File path = new File(pathTemp);
        path.mkdir();
        path = new File(pathUpload);
        path.mkdir();
        return pathTemp;
    }

    private List<FileItem> getFileItems(HttpServletRequest request, String pathTemp) {
        DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
        diskFileItemFactory.setRepository(new File(pathTemp));
        diskFileItemFactory.setSizeThreshold(10240);
        ServletFileUpload servletFileUpload = new ServletFileUpload(diskFileItemFactory);
        List<FileItem> items = null;
        try {
            items = servletFileUpload.parseRequest(request);
        } catch (FileUploadException e) {
            e.printStackTrace();
        }
        return items;
    }

    private UserEntity getUserEntity(List<FileItem> items) {
        UserEntity userEntity = new UserEntity();

        for (FileItem item : items) {
            if (item.isFormField()) {
                String fileName = item.getFieldName();
                if (fileName.equals("name")) {
                    userEntity.setUserName(item.getString());
                } else if (fileName.equals("psw")) {
                    userEntity.setPassword(item.getString());
                } else if (fileName.equals("tel")) {
                    userEntity.setTel(item.getString());
                } else if (fileName.equals("idCard")) {
                    userEntity.setIdCard(item.getString());
                }
                System.out.print(item.getFieldName() + ":");
                System.out.println(item.getString());
            } else {
                String pic = item.getFieldName();
                userEntity.setLicense(item.getName());
                System.out.println(item.getName());
            }
        }
        return userEntity;
    }


}
