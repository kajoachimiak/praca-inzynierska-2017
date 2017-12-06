package com.pracainzynierska.model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by karol on 06.12.17.
 */
@Entity
@Table(name = "PRZEDMIOT")
public class Przedmiot {
    private Integer id;
    private String nazwa;
    private String opis;
    private List<Edycja> edycjaList;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "NAZWA")
    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    @Basic
    @Column(name = "OPIS")
    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "przedmiot", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    public List<Edycja> getEdycjaList() {
        return edycjaList;
    }

    public void setEdycjaList(List<Edycja> edycjaList) {
        this.edycjaList = edycjaList;
    }
}