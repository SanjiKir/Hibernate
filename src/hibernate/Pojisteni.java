/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hibernate;

import java.util.Objects;

/**
 *
 * @author shak01
 */
public class Pojisteni {

    private Long pojisteniId;
    private String nazevPojisteni;
    private String typPojisteni;

    public Pojisteni() {
    }

    public Pojisteni (String nazevPojisteni, String typPojisteni) {
        this.nazevPojisteni = nazevPojisteni;
        this.typPojisteni = typPojisteni;
    }

    public Long getPojisteniId() {
        return pojisteniId;
    }

   

    public void setPojisteniId(Long pojisteniId) {
        this.pojisteniId = pojisteniId;
    }

    public String getNazevPojisteni() {
        return nazevPojisteni;
    }

    public void setNazevPojisteni(String nazevPojisteni) {
        this.nazevPojisteni = nazevPojisteni;
    }

    public String getTypPojisteni() {
        return typPojisteni;
    }

    public void setTypPojisteni(String typPojisteni) {
        this.typPojisteni = typPojisteni;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Pojisteni other = (Pojisteni) obj;
        if (!Objects.equals(this.pojisteniId, other.pojisteniId)) {
            return false;
        }
        return true;
    }
}
