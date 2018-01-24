package com.pracainzynierska.model.entities;

import javax.persistence.*;

/**
 * Created by karol on 06.12.17.
 */
@Entity
@Table(name = "SZABLONY")
public class Szablony{
    private Integer id;
    private Przedmiot przedmiot;
    private Uczestnik uczestnik;
    private Grupa grupa;
    private Edycja edycja;
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

    @JoinColumn(name = "PRZEDMIOT_ID", referencedColumnName = "ID")
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    public Przedmiot getPrzedmiot() {
        return przedmiot;
    }

    public void setPrzedmiot(Przedmiot przedmiot) {
        this.przedmiot = przedmiot;
    }

    @JoinColumn(name = "UCZESTNIK_ID", referencedColumnName = "ID")
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    public Uczestnik getUczestnik() {
        return uczestnik;
    }

    public void setUczestnik(Uczestnik uczestnikId) {
        this.uczestnik = uczestnikId;
    }

    @JoinColumn(name = "GRUPA_ID", referencedColumnName = "ID")
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    public Grupa getGrupa() {
        return grupa;
    }

    public void setGrupa(Grupa grupaId) {
        this.grupa = grupaId;
    }

    @JoinColumn(name = "EDYCJA_ID", referencedColumnName = "ID")
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    public Edycja getEdycja() {
        return edycja;
    }

    public void setEdycja(Edycja edycjaId) {
        this.edycja = edycjaId;
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

    public Szablony() {
    }

    public Szablony(Integer id, Przedmiot przedmiot, Uczestnik uczestnik,
                    Grupa grupa, Edycja edycja, String nazwa, String tresc, String opis) {
        this.id = id;
        this.przedmiot = przedmiot;
        this.uczestnik = uczestnik;
        this.grupa = grupa;
        this.edycja = edycja;
        this.nazwa = nazwa;
        this.tresc = tresc;
        this.opis = opis;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Szablony szablony = (Szablony) o;

        if (id != null ? !id.equals(szablony.id) : szablony.id != null) return false;
        if (uczestnik != null ? !uczestnik.equals(szablony.uczestnik) : szablony.uczestnik != null)
            return false;
        if (grupa != null ? !grupa.equals(szablony.grupa) : szablony.grupa != null) return false;
        if (edycja != null ? !edycja.equals(szablony.edycja) : szablony.edycja != null) return false;
        if (nazwa != null ? !nazwa.equals(szablony.nazwa) : szablony.nazwa != null) return false;
        if (tresc != null ? !tresc.equals(szablony.tresc) : szablony.tresc != null) return false;
        if (opis != null ? !opis.equals(szablony.opis) : szablony.opis != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (uczestnik != null ? uczestnik.hashCode() : 0);
        result = 31 * result + (grupa != null ? grupa.hashCode() : 0);
        result = 31 * result + (edycja != null ? edycja.hashCode() : 0);
        result = 31 * result + (nazwa != null ? nazwa.hashCode() : 0);
        result = 31 * result + (tresc != null ? tresc.hashCode() : 0);
        result = 31 * result + (opis != null ? opis.hashCode() : 0);
        return result;
    }
}
