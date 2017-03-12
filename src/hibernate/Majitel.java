/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hibernate;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Majitel {

    private Long idMajitel;
    private String firstName;
    private String lastName;
    private String email;
    private String mobileNumber;

    private Set<Auto> auta = new HashSet<Auto>();

    public Majitel(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Majitel() {

    }


    public void pridejAuto(Auto auto) {
        auta.add(auto);
    }

    public Set<Auto> getAuta() {
        return auta;
    }

    public void setAuta(Set<Auto> auta) {
        this.auta = auta;
    }

    public Long getId() {
        return idMajitel;
    }

    private void setId(Long id) {
        this.idMajitel = idMajitel;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + Objects.hashCode(this.idMajitel);
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
        final Majitel other = (Majitel) obj;
        if (!Objects.equals(this.idMajitel, other.idMajitel)) {
            return false;
        }
        return true;
    }

    public Long getIdMajitel() {
        return idMajitel;
    }

    public void setIdMajitel(Long idMajitel) {
        this.idMajitel = idMajitel;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }
}
