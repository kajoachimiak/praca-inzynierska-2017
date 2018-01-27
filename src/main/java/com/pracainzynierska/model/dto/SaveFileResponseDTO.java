package com.pracainzynierska.model.dto;

/**
 * Created by karol on 27.01.18.
 */
public class SaveFileResponseDTO {
    private boolean isWritingSuccess;

    public SaveFileResponseDTO() {
    }

    public SaveFileResponseDTO(boolean isWritingSuccess) {
        this.isWritingSuccess = isWritingSuccess;
    }

    public boolean isWritingSuccess() {
        return isWritingSuccess;
    }

    public void setWritingSuccess(boolean writingSuccess) {
        isWritingSuccess = writingSuccess;
    }
}
