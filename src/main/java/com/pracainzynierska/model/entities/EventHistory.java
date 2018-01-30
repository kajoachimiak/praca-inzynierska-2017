package com.pracainzynierska.model.entities;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by karol on 30.01.18.
 */
@Entity
@Table(name = "event_history")
public class EventHistory {
    private Integer id;
    private Date executionTime;
    private Template template;
    private String content;
    private String result;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "execution_time")
    public Date getExecutionTime() {
        return executionTime;
    }

    public void setExecutionTime(Date executionTime) {
        this.executionTime = executionTime;
    }

    @JoinColumn(name = "template_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    public Template getTemplate() {
        return template;
    }

    public void setTemplate(Template templateId) {
        this.template = templateId;
    }

    @Basic
    @Column(name = "content")
    public String getContent() {
        return content;
    }

    public void setContent(String command) {
        this.content = command;
    }

    @Basic
    @Column(name = "result")
    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EventHistory that = (EventHistory) o;

        if (!id.equals(that.id)) return false;
        if (!executionTime.equals(that.executionTime)) return false;
        return template.equals(that.template);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + executionTime.hashCode();
        result = 31 * result + template.hashCode();
        return result;
    }
}
