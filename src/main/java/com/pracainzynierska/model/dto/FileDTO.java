package com.pracainzynierska.model.dto;

/**
 * Created by karol on 27.01.18.
 */
public class FileDTO {
    private String fileContent;

    public FileDTO() {
    }

    public FileDTO(String fileContent) {
        this.fileContent = fileContent;
    }

    public String getFileContent() {
        return fileContent;
    }

    public void setFileContent(String fileContent) {
        this.fileContent = fileContent;
    }
}
