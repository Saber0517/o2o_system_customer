package com.jyhon.serviceimpl;

import com.jyhon.service.FileService;
import com.jyhon.dao.UploadFileEntityDao;

import java.io.InputStream;

/**
 * Created by WhiteSaber on 15/8/9.
 */
public class FileServiceImpl implements FileService {
    static UploadFileEntityDao uploadFileService;

    public UploadFileEntityDao getUploadFileService() {
        return uploadFileService;
    }

    public void setUploadFileService(UploadFileEntityDao uploadFileService) {
        this.uploadFileService = uploadFileService;
    }
    //    UploadFileEntityDao uploadFileService = new UploadFileEntityDaoImple();

    public InputStream getFile(String fileName) {
        return uploadFileService.getFile(fileName);
    }

    public byte[] getFileByByte(String fileName) {
        return uploadFileService.getFileByByte(fileName);
    }
}
