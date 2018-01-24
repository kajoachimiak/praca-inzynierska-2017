package com.pracainzynierska.model.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by karol on 06.12.17.
 */
@Entity
@Table(name = "UCZESTNIK")
@NamedQueries({
        @NamedQuery(
                name = Uczestnik.FIND_BY_LOGIN,
                query = "select u from Uczestnik u where u.login = :login"
        )
})
public class Uczestnik {
    public static final String FIND_BY_LOGIN = "UCZESTNIK.findByLogin";
    private Integer id;
    private String opis;
    private String login;
    private String haslo;
    private Grupa grupa;
    private Rola rola;
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
    @Column(name = "OPIS")
    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    @Basic
    @Column(name = "HASLO")
    public String getHaslo() {
        return haslo;
    }

    public void setHaslo(String haslo) {
        this.haslo = haslo;
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

    @JoinColumn(name = "ROLA_ID", referencedColumnName = "ID")
    @ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    public Rola getRola() {
        return this.rola;
    }

    public void setRola(Rola rola) {
        this.rola = rola;
    }


    @OneToMany(fetch = FetchType.LAZY, mappedBy = "uczestnik", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    public List<Szablony> getSzablonyList() {
        return szablonyList;
    }

    public void setSzablonyList(List<Szablony> szablonyList) {
        this.szablonyList = szablonyList;
    }

    public Uczestnik() {
    }

    public Uczestnik(String opis, String login, String haslo, Grupa grupa, Rola rola) {
        this.opis = opis;
        this.login = login;
        this.haslo = haslo;
        this.grupa = grupa;
        this.rola = rola;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Uczestnik uczestnik = (Uczestnik) o;

        if (id != null ? !id.equals(uczestnik.id) : uczestnik.id != null) return false;
        if (opis != null ? !opis.equals(uczestnik.opis) : uczestnik.opis != null) return false;
        if (login != null ? !login.equals(uczestnik.login) : uczestnik.login != null) return false;
        if (haslo != null ? !haslo.equals(uczestnik.haslo) : uczestnik.haslo != null) return false;
        if (grupa != null ? !grupa.equals(uczestnik.grupa) : uczestnik.grupa != null) return false;
        return rola != null ? rola.equals(uczestnik.rola) : uczestnik.rola == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (opis != null ? opis.hashCode() : 0);
        result = 31 * result + (login != null ? login.hashCode() : 0);
        result = 31 * result + (haslo != null ? haslo.hashCode() : 0);
        result = 31 * result + (grupa != null ? grupa.hashCode() : 0);
        result = 31 * result + (rola != null ? rola.hashCode() : 0);
        return result;
    }
}
