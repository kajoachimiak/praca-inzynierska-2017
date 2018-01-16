package com.pracainzynierska.model.entities;

import javax.persistence.*;

/**
 * Created by karol on 16.01.18.
 */
@Entity
@Table(name = "ROLA")
public class Rola {
    private Integer id;
    private String nazwa;
    private Uczestnik uczestnik;

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

    @JoinColumn(name = "UCZESTNIK_ID", referencedColumnName = "ID")
    @ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    public Uczestnik getUczestnik(){
        return this.uczestnik;
    }

    public void setUczestnik(Uczestnik uczestnik) {
        this.uczestnik = uczestnik;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Rola rola = (Rola) o;

        if (id != null ? !id.equals(rola.id) : rola.id != null) return false;
        return nazwa != null ? nazwa.equals(rola.nazwa) : rola.nazwa == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (nazwa != null ? nazwa.hashCode() : 0);
        return result;
    }
}
