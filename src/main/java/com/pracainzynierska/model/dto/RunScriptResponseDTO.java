package com.pracainzynierska.model.dto;

/**
 * Created by karol on 26.01.18.
 */
public class RunScriptResponseDTO {
    private String message;
    private boolean executionSuccess;

    public RunScriptResponseDTO() {
    }

    public RunScriptResponseDTO(String message, boolean executionSuccess) {
        this.message = message;
        this.executionSuccess = executionSuccess;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isExecutionSuccess() {
        return executionSuccess;
    }

    public void setExecutionSuccess(boolean executionSuccess) {
        this.executionSuccess = executionSuccess;
    }
}
