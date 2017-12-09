package com.pracainzynierska.model;

import com.pracainzynierska.dict.Queries;

import javax.persistence.*;

/**
 * Created by karol on 06.12.17.
 */
@Entity
@NamedQueries(value = {
        @NamedQuery(name = Queries.Szablony.getById, query = "select s from Szablony s where s.id = ?1")
})
@Table(name = "SZABLONY")
public class Szablony{
    private Integer id;
    private Integer uczestnikId;
    private Integer grupaId;
    private Integer edycjaId;
    private String nazwa;
    private String tresc;
    private String opis;

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
    @Column(name = "UCZESTNIK_ID")
    public Integer getUczestnikId() {
        return uczestnikId;
    }

    public void setUczestnikId(Integer uczestnikId) {
        this.uczestnikId = uczestnikId;
    }

    @Basic
    @Column(name = "GRUPA_ID")
    public Integer getGrupaId() {
        return grupaId;
    }

    public void setGrupaId(Integer grupaId) {
        this.grupaId = grupaId;
    }

    @Basic
    @Column(name = "EDYCJA_ID")
    public Integer getEdycjaId() {
        return edycjaId;
    }

    public void setEdycjaId(Integer edycjaId) {
        this.edycjaId = edycjaId;
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
    @Column(name = "TRESC")
    public String getTresc() {
        return tresc;
    }

    public void setTresc(String tresc) {
        this.tresc = tresc;
    }

    @Basic
    @Column(name = "OPIS")
    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Szablony szablony = (Szablony) o;

        if (id != null ? !id.equals(szablony.id) : szablony.id != null) return false;
        if (uczestnikId != null ? !uczestnikId.equals(szablony.uczestnikId) : szablony.uczestnikId != null)
            return false;
        if (grupaId != null ? !grupaId.equals(szablony.grupaId) : szablony.grupaId != null) return false;
        if (edycjaId != null ? !edycjaId.equals(szablony.edycjaId) : szablony.edycjaId != null) return false;
        if (nazwa != null ? !nazwa.equals(szablony.nazwa) : szablony.nazwa != null) return false;
        if (tresc != null ? !tresc.equals(szablony.tresc) : szablony.tresc != null) return false;
        if (opis != null ? !opis.equals(szablony.opis) : szablony.opis != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (uczestnikId != null ? uczestnikId.hashCode() : 0);
        result = 31 * result + (grupaId != null ? grupaId.hashCode() : 0);
        result = 31 * result + (edycjaId != null ? edycjaId.hashCode() : 0);
        result = 31 * result + (nazwa != null ? nazwa.hashCode() : 0);
        result = 31 * result + (tresc != null ? tresc.hashCode() : 0);
        result = 31 * result + (opis != null ? opis.hashCode() : 0);
        return result;
    }
}
