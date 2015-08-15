package com.oocl.jyhon.serviceimpl;

import com.oocl.jyhon.dao.UploadFileEntityDao;
import com.oocl.jyhon.daoimple.UploadFileEntityDaoImple;
import com.oocl.jyhon.service.FileService;

import java.io.InputStream;

/**
 * Created by WhiteSaber on 15/8/9.
 */
public class FileServiceImpl implements FileService {
    UploadFileEntityDao uploadFileService = new UploadFileEntityDaoImple();

    public InputStream getFile(String fileName) {
        return uploadFileService.getFile(fileName);
    }

    public byte[] getFileByByte(String fileName) {
        return uploadFileService.getFileByByte(fileName);
    }
}
