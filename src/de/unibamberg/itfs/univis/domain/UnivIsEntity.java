package de.unibamberg.itfs.univis.domain;

import javax.xml.bind.annotation.*;

/**
 *
 * @author gtudan
 */
@XmlAccessorType(XmlAccessType.FIELD)
public abstract class UnivIsEntity {

    @XmlElement
    long id;
    @XmlID
    @XmlAttribute
    String key;
    @XmlElement(name = "orgname")
    String orgName;

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
    @XmlElementWrapper(name="orgunits")
    @XmlElement(name="orgunit")
    String[] orgUnits;
}
