package com.jyhon.util;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ZHANGJA4 on 8/7/2015.
 */
public class FormDataUtil {

    public List<FileItem> getFileItems(HttpServletRequest request, String pathTemp) {
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

    public Map<String, String> getParamsUTF8(List<FileItem> items) throws UnsupportedEncodingException {
        Map<String, String> fieldMap = new LinkedHashMap<String, String>();
        for (FileItem fileItem : items) {
            if (fileItem.isFormField()) {
                fieldMap.put(fileItem.getFieldName(), fileItem.getString("UTF-8"));
            }
        }
        return fieldMap;
    }



    public String InitFileFolder(ServletContext servletContext) {
        String pathTemp = servletContext.getRealPath("\\temp");
        System.out.println(pathTemp);
        String pathUpload = servletContext.getRealPath("/upload");
        System.out.println(pathUpload);
        File path = new File(pathTemp);
        path.mkdir();
        path = new File(pathUpload);
        path.mkdir();
        return pathTemp;
    }
}
