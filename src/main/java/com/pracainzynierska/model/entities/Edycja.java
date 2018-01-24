package com.pracainzynierska.model.entities;

import javax.persistence.*;
import java.util.List;

/**
 * Created by karol on 06.12.17.
 */
@Entity
@Table(name = "EDYCJA")
public class Edycja {
    private Integer id;
    private String nazwa;
    private String opis;
    private Przedmiot przedmiot;
    private List<Grupa> grupaList;
    private List<Szablony> szablonyList;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
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

    @JoinColumn(name = "PRZEDMIOT_ID", referencedColumnName = "ID")
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    public Przedmiot getPrzedmiot() {
        return przedmiot;
    }

    public void setPrzedmiot(Przedmiot przedmiot) {
        this.przedmiot = przedmiot;
    }

    @OneToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, mappedBy = "edycja", fetch = FetchType.LAZY)
    public List<Grupa> getGrupaList() {
        return grupaList;
    }

    public void setGrupaList(List<Grupa> grupaList) {
        this.grupaList = grupaList;
    }


    @OneToMany(fetch = FetchType.LAZY, mappedBy = "edycja", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    public List<Szablony> getSzablonyList() {
        return szablonyList;
    }

    public void setSzablonyList(List<Szablony> szablonyList) {
        this.szablonyList = szablonyList;
    }

    public Edycja() {
    }

    public Edycja(Integer id, String nazwa, String opis, Przedmiot przedmiot) {
        this.id = id;
        this.nazwa = nazwa;
        this.opis = opis;
        this.przedmiot = przedmiot;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Edycja edycja = (Edycja) o;

        if (!id.equals(edycja.id)) return false;
        if (nazwa != null ? !nazwa.equals(edycja.nazwa) : edycja.nazwa != null) return false;
        if (opis != null ? !opis.equals(edycja.opis) : edycja.opis != null) return false;
        if (przedmiot != null ? !przedmiot.equals(edycja.przedmiot) : edycja.przedmiot != null) return false;
        return grupaList != null ? grupaList.equals(edycja.grupaList) : edycja.grupaList == null;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + (nazwa != null ? nazwa.hashCode() : 0);
        result = 31 * result + (opis != null ? opis.hashCode() : 0);
        result = 31 * result + (przedmiot != null ? przedmiot.hashCode() : 0);
        result = 31 * result + (grupaList != null ? grupaList.hashCode() : 0);
        return result;
    }
}
