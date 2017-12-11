package com.pracainzynierska.model;

import javax.persistence.*;

/**
 * Created by karol on 06.12.17.
 */
@Entity
@Table(name = "UCZESTNIK")
public class Uczestnik {
    private Integer id;
    private String opis;
    private String login;
    private Grupa grupa;

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
    @Column(name = "OPIS")
    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    @Basic
    @Column(name = "LOGIN")
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @JoinColumn(name = "GRUPA_ID", referencedColumnName = "ID")
    @ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    public Grupa getGrupa() {
        return grupa;
    }

    public void setGrupa(Grupa grupa) {
        this.grupa = grupa;
    }

    public Uczestnik() {
    }

    public Uczestnik(Integer id, String opis, String login, Grupa grupa) {
        this.id = id;
        this.opis = opis;
        this.login = login;
        this.grupa = grupa;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Uczestnik uczestnik = (Uczestnik) o;

        if (!id.equals(uczestnik.id)) return false;
        if (opis != null ? !opis.equals(uczestnik.opis) : uczestnik.opis != null) return false;
        if (login != null ? !login.equals(uczestnik.login) : uczestnik.login != null) return false;
        return grupa != null ? grupa.equals(uczestnik.grupa) : uczestnik.grupa == null;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + (opis != null ? opis.hashCode() : 0);
        result = 31 * result + (login != null ? login.hashCode() : 0);
        result = 31 * result + (grupa != null ? grupa.hashCode() : 0);
        return result;
    }
}
