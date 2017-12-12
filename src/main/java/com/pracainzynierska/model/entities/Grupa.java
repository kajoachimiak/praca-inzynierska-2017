package com.pracainzynierska.model.entities;

import javax.persistence.*;
import java.util.List;

/**
 * Created by karol on 06.12.17.
 */
@Entity
@Table(name = "GRUPA")
public class Grupa {
    private Integer id;
    private String nazwa;
    private String opis;
    private Edycja edycja;
    private List<Uczestnik> uczestnikList;

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

    @JoinColumn(name = "EDYCJA_ID", referencedColumnName = "ID")
    @ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    public Edycja getEdycja() {
        return edycja;
    }

    public void setEdycja(Edycja edycja) {
        this.edycja = edycja;
    }

    @OneToMany(cascade = { CascadeType.MERGE, CascadeType.PERSIST }, mappedBy = "grupa", fetch = FetchType.LAZY)
    public List<Uczestnik> getUczestnikList() {
        return uczestnikList;
    }

    public void setUczestnikList(List<Uczestnik> uczestnikList) {
        this.uczestnikList = uczestnikList;
    }

    public Grupa() {
    }

    public Grupa(Integer id, String nazwa, String opis, Edycja edycja) {
        this.id = id;
        this.nazwa = nazwa;
        this.opis = opis;
        this.edycja = edycja;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Grupa grupa = (Grupa) o;

        if (!id.equals(grupa.id)) return false;
        if (nazwa != null ? !nazwa.equals(grupa.nazwa) : grupa.nazwa != null) return false;
        if (opis != null ? !opis.equals(grupa.opis) : grupa.opis != null) return false;
        if (edycja != null ? !edycja.equals(grupa.edycja) : grupa.edycja != null) return false;
        return uczestnikList != null ? uczestnikList.equals(grupa.uczestnikList) : grupa.uczestnikList == null;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + (nazwa != null ? nazwa.hashCode() : 0);
        result = 31 * result + (opis != null ? opis.hashCode() : 0);
        result = 31 * result + (edycja != null ? edycja.hashCode() : 0);
        result = 31 * result + (uczestnikList != null ? uczestnikList.hashCode() : 0);
        return result;
    }
}
