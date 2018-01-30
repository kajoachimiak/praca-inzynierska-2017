package com.pracainzynierska.model.dto;

import java.util.Date;

/**
 * Created by karol on 30.01.18.
 */
public class EventHistoryResponseElementDTO {
    private String course;
    private String edition;
    private String group;
    private String user;
    private String content;
    private String executionTime;

    public EventHistoryResponseElementDTO() {
    }

    public EventHistoryResponseElementDTO(String course, String edition, String group,
                                          String user, String content, String executionTime) {
        this.course = course;
        this.edition = edition;
        this.group = group;
        this.user = user;
        this.content = content;
        this.executionTime = executionTime;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getExecutionTime() {
        return executionTime;
    }

    public void setExecutionTime(String executionTime) {
        this.executionTime = executionTime;
    }
}
