package com.pracainzynierska.model.entities;

import javax.persistence.*;

/**
 * Created by karol on 06.12.17.
 */
@Entity
@Table(name = "template")
public class Template {
    private Integer id;
    private Course course;
    private User user;
    private Group group;
    private Edition edition;
    private String name;
    private String content;
    private String description;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @JoinColumn(name = "course_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    public User getUser() {
        return user;
    }

    public void setUser(User userId) {
        this.user = userId;
    }

    @JoinColumn(name = "group_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    public Group getGroup() {
        return group;
    }

    public void setGroup(Group groupId) {
        this.group = groupId;
    }

    @JoinColumn(name = "edition_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    public Edition getEdition() {
        return edition;
    }

    public void setEdition(Edition editionId) {
        this.edition = editionId;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String nazwa) {
        this.name = nazwa;
    }

    @Basic
    @Column(name = "content")
    public String getContent() {
        return content;
    }

    public void setContent(String tresc) {
        this.content = tresc;
    }

    @Basic
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String opis) {
        this.description = opis;
    }

    public Template() {
    }

    public Template(Integer id, Course course, User user,
                    Group group, Edition edition, String name, String content, String description) {
        this.id = id;
        this.course = course;
        this.user = user;
        this.group = group;
        this.edition = edition;
        this.name = name;
        this.content = content;
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Template template = (Template) o;

        if (id != null ? !id.equals(template.id) : template.id != null) return false;
        if (user != null ? !user.equals(template.user) : template.user != null)
            return false;
        if (group != null ? !group.equals(template.group) : template.group != null) return false;
        if (edition != null ? !edition.equals(template.edition) : template.edition != null) return false;
        if (name != null ? !name.equals(template.name) : template.name != null) return false;
        if (content != null ? !content.equals(template.content) : template.content != null) return false;
        if (description != null ? !description.equals(template.description) : template.description != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (user != null ? user.hashCode() : 0);
        result = 31 * result + (group != null ? group.hashCode() : 0);
        result = 31 * result + (edition != null ? edition.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }
}
