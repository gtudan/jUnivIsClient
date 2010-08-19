package de.unibamberg.itfs.univis.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Gregor Tudan
 */
@XmlRootElement(name="Org")
@XmlAccessorType(XmlAccessType.FIELD)
public class Department extends UnivIsEntity{
    private long orgnr;
    private String email;
    private String fax;
    private String name;
    private int lehr;
    private String pubser;
    private String resconf;
    private String rescoop;
    private String street;
    @XmlElement(name="ort")
    private String city;
    private String tel;
    private String url;
            
    //@XmlElement(name="job")
    //@Xml
    //private List<Job> jobs;
    
    @Override
    public String getOrgName() {
    	return this.name;
    }

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

    public int getLehr() {
        return lehr;
    }

    public void setLehr(int lehr) {
        this.lehr = lehr;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getOrgnr() {
        return orgnr;
    }

    public void setOrgnr(long orgnr) {
        this.orgnr = orgnr;
    }

    public String getPubser() {
        return pubser;
    }

    public void setPubser(String pubser) {
        this.pubser = pubser;
    }

    public String getResconf() {
        return resconf;
    }

    public void setResconf(String resconf) {
        this.resconf = resconf;
    }

    public String getRescoop() {
        return rescoop;
    }

    public void setRescoop(String rescoop) {
        this.rescoop = rescoop;
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
