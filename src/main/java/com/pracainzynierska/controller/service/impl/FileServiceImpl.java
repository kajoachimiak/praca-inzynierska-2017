package com.pracainzynierska.controller.service.impl;

import com.pracainzynierska.controller.service.FileService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by karol on 26.01.18.
 */
@Service
public class FileServiceImpl implements FileService {
    private static final Logger LOG = Logger.getLogger(FileServiceImpl.class);
    @Override
    public String getFileContent(String filePath) throws IOException {
        StringBuilder result = new StringBuilder();
        BufferedReader br = Files.newBufferedReader(Paths.get(filePath));
        br.lines().collect(Collectors.toList()).forEach(result::append);
        return result.toString();
    }

    @Override
    public void writeToFile(String path, String content) throws IOException {
        File file = new File(path);

        try (FileOutputStream fop = new FileOutputStream(file)) {
            if (!file.exists()) {
                file.createNewFile();
            }
            byte[] contentInBytes = content.getBytes();
            fop.write(contentInBytes);
            fop.flush();
            fop.close();
        }catch (IOException e){
            LOG.error("Error writing to file in path: " + path, e);
            throw new IOException();
        }
    }
}
