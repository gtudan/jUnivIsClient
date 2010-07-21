package de.unibamberg.itfs.univis.domain;

import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author gtudan
 */
@XmlRootElement(name="Person")
@XmlAccessorType(XmlAccessType.FIELD)
public class Person extends UnivIsEntity {

    @XmlElement(name="firstname")
    String firstName;

    @XmlElement(name="lastname")
    String lastName;

    @XmlElement(name="lehr")
    boolean lecturerer;

    @XmlElement(defaultValue="true")
    boolean visible;

    @XmlElementWrapper(name="locations")
    @XmlElement(name="location",type=Location.class)
    List<Location> locations;

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

    public boolean isLecturerer() {
        return lecturerer;
    }

    public void setLecturerer(boolean lecturerer) {
        this.lecturerer = lecturerer;
    }

    public List<Location> getLocations() {
        return locations;
    }

    public void setLocations(List<Location> locations) {
        this.locations = locations;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

}
