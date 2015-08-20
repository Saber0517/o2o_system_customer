package com.oocl.jyhon.servlet.file;

import com.oocl.jyhon.service.FileService;
import com.oocl.jyhon.serviceimpl.FileServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by WhiteSaber on 15/8/9.
 */
@WebServlet(name = "FileServlet", urlPatterns = {"/File/*"})
public class FileServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String fileName = request.getParameter("filename");
        FileService fileService = new FileServiceImpl();
//        InputStream inputStream = fileService.getFile(fileName);
        byte[] inputBytes = fileService.getFileByByte(fileName);
        response.getOutputStream().write(inputBytes);
        response.getOutputStream().flush();
        response.getOutputStream().close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
