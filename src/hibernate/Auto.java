/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hibernate;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author shak01
 */
public class Auto {

    private Long autoId;
    private String nazev;
    private Majitel majitel;

    public Auto(String nazev) {
        this.nazev = nazev;
    }

    public Auto() {
    }
    
    
    
    public Long getAutoId() {
        return autoId;
    }

    public void setAutoId(Long autoId) {
        this.autoId = autoId;
    }

    public String getNazev() {
        return nazev;
    }

    public void setNazev(String nazev) {
        this.nazev = nazev;
    }

    public Majitel getMajitel() {
        return majitel;
    }

    public void setMajitel(Majitel majitel) {
        this.majitel = majitel;
    }

}
