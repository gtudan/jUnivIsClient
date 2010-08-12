package de.unibamberg.itfs.univis.domain;

import javax.xml.bind.annotation.*;

/**
 *
 * @author gtudan
 */
@XmlAccessorType(XmlAccessType.FIELD)
public abstract class UnivIsEntity {

    @XmlElement
    private long id;
    @XmlID
    @XmlAttribute
    private String key;
    @XmlElement(name = "orgname")
    private String orgName;

    @XmlElementWrapper(name="orgunits")
    @XmlElement(name="orgunit")
    String[] orgUnits;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String[] getOrgUnits() {
        return orgUnits;
    }

    public void setOrgUnits(String[] orgUnits) {
        this.orgUnits = orgUnits;
    }

}
