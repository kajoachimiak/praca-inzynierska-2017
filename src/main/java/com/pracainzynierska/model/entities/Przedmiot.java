package com.pracainzynierska.model.entities;

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

    public Przedmiot() {
    }

    public Przedmiot(Integer id, String nazwa, String opis) {
        this.id = id;
        this.nazwa = nazwa;
        this.opis = opis;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Przedmiot przedmiot = (Przedmiot) o;

        if (!id.equals(przedmiot.id)) return false;
        if (nazwa != null ? !nazwa.equals(przedmiot.nazwa) : przedmiot.nazwa != null) return false;
        if (opis != null ? !opis.equals(przedmiot.opis) : przedmiot.opis != null) return false;
        return edycjaList != null ? edycjaList.equals(przedmiot.edycjaList) : przedmiot.edycjaList == null;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + (nazwa != null ? nazwa.hashCode() : 0);
        result = 31 * result + (opis != null ? opis.hashCode() : 0);
        result = 31 * result + (edycjaList != null ? edycjaList.hashCode() : 0);
        return result;
    }
}