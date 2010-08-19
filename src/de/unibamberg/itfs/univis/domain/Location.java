/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.unibamberg.itfs.univis.domain;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAccessType;
/**
 *
 * @author gtudan
 */
@XmlRootElement(name="Location")
@XmlAccessorType(XmlAccessType.FIELD)
public class Location {
    @XmlElement
    String office;
    @XmlElement
    String street;
    @XmlElement(name="ort")
    String city;

    @XmlElement
    String tel;
    @XmlElement
    String fax;
    @XmlElement
    String email;
    @XmlElement
    String url;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getOffice() {
        return office;
    }

    public void setOffice(String office) {
        this.office = office;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    
}
