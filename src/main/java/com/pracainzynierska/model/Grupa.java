package com.pracainzynierska.model;

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
}
