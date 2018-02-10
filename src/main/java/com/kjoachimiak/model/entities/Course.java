package com.kjoachimiak.model.entities;

import javax.persistence.*;
import java.util.List;

/**
 * Created by karol on 06.12.17.
 */
@Entity
@Table(name = "course")
public class Course {
    private Integer id;
    private String name;
    private String description;
    private List<Edition> editionList;
    private List<Template> templateList;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String opis) {
        this.description = opis;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "course", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    public List<Edition> getEditionList() {
        return editionList;
    }

    public void setEditionList(List<Edition> editionList) {
        this.editionList = editionList;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "course", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    public List<Template> getTemplateList() {
        return templateList;
    }

    public void setTemplateList(List<Template> templateList) {
        this.templateList = templateList;
    }

    public Course() {
    }

    public Course(Integer id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Course course = (Course) o;

        if (!id.equals(course.id)) return false;
        if (name != null ? !name.equals(course.name) : course.name != null) return false;
        if (description != null ? !description.equals(course.description) : course.description != null) return false;
        return editionList != null ? editionList.equals(course.editionList) : course.editionList == null;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (editionList != null ? editionList.hashCode() : 0);
        return result;
    }
}