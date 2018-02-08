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
    private String userContext;
    private String groupContext;
    private String editionContext;
    private String courseContext;

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
    @Column(name = "user_context")
    public String getUserContext() {
        return userContext;
    }

    public void setUserContext(String userContext) {
        this.userContext = userContext;
    }

    @Basic
    @Column(name = "group_context")
    public String getGroupContext() {
        return groupContext;
    }

    public void setGroupContext(String groupContext) {
        this.groupContext = groupContext;
    }

    @Basic
    @Column(name = "edition_context")
    public String getEditionContext() {
        return editionContext;
    }

    public void setEditionContext(String editionContext) {
        this.editionContext = editionContext;
    }

    @Basic
    @Column(name = "course_context")
    public String getCourseContext() {
        return courseContext;
    }

    public void setCourseContext(String courseContext) {
        this.courseContext = courseContext;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EventHistory that = (EventHistory) o;

        if (!id.equals(that.id)) return false;
        if (!executionTime.equals(that.executionTime)) return false;
        if (!template.equals(that.template)) return false;
        if (!content.equals(that.content)) return false;
        if (userContext != null ? !userContext.equals(that.userContext) : that.userContext != null) return false;
        if (groupContext != null ? !groupContext.equals(that.groupContext) : that.groupContext != null) return false;
        if (editionContext != null ? !editionContext.equals(that.editionContext) : that.editionContext != null)
            return false;
        return courseContext != null ? courseContext.equals(that.courseContext) : that.courseContext == null;
    }

    @Override
    public int hashCode() {
        int result1 = id.hashCode();
        result1 = 31 * result1 + executionTime.hashCode();
        result1 = 31 * result1 + template.hashCode();
        result1 = 31 * result1 + content.hashCode();
        result1 = 31 * result1 + (userContext != null ? userContext.hashCode() : 0);
        result1 = 31 * result1 + (groupContext != null ? groupContext.hashCode() : 0);
        result1 = 31 * result1 + (editionContext != null ? editionContext.hashCode() : 0);
        result1 = 31 * result1 + (courseContext != null ? courseContext.hashCode() : 0);
        return result1;
    }
}
