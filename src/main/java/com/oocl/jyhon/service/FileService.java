package com.oocl.jyhon.service;

import java.io.InputStream;

/**
 * Created by WhiteSaber on 15/8/9.
 */
public interface FileService {
    InputStream getFile(String fileName);
    byte[] getFileByByte(String fileName);
}
