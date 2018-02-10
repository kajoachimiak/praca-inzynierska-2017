package com.kjoachimiak.dto;

import java.util.List;

/**
 * Created by karol on 30.01.18.
 */
public class EventHistoryResponseDTO {
    private int templateId;
    private String templateName;
    private List<EventHistoryResponseElementDTO> templateHistory;

    public EventHistoryResponseDTO() {
    }

    public EventHistoryResponseDTO(int templateId, String templateName,
                                   List<EventHistoryResponseElementDTO> templateHistory) {
        this.templateId = templateId;
        this.templateName = templateName;
        this.templateHistory = templateHistory;
    }

    public int getTemplateId() {
        return templateId;
    }

    public void setTemplateId(int templateId) {
        this.templateId = templateId;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public List<EventHistoryResponseElementDTO> getTemplateHistory() {
        return templateHistory;
    }

    public void setTemplateHistory(List<EventHistoryResponseElementDTO> templateHistory) {
        this.templateHistory = templateHistory;
    }
}
