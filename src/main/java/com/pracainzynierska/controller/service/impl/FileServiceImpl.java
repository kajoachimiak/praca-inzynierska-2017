package com.pracainzynierska.controller.service.impl;

import com.pracainzynierska.controller.service.FileService;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;

/**
 * Created by karol on 26.01.18.
 */
@Service
public class FileServiceImpl implements FileService {
    @Override
    public String getFileContent(String filePath) throws IOException {
        StringBuilder result = new StringBuilder();
        BufferedReader br = Files.newBufferedReader(Paths.get(filePath));
        br.lines().collect(Collectors.toList()).forEach(result::append);
        return result.toString();
    }
}
