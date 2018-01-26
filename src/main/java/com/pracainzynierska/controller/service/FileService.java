package com.pracainzynierska.controller.service;

import java.io.IOException;

/**
 * Created by karol on 26.01.18.
 */
public interface FileService {
    String getFileContent(String path) throws IOException;
}
