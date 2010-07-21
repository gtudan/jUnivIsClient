package de.unibamberg.itfs.univis.domain;

import java.util.List;
import javax.xml.bind.annotation.*;

/**
 *
 * @author gtudan
 */

@XmlRootElement(name="Room")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="Room")
public class Room extends UnivIsEntity{


    @XmlElement
    String address;

    @XmlElement
    String description;

    @XmlElement(name="short")
    String shortName;

    @XmlElement
    String name;

    @XmlElement
    int size;

//  This requires XML-Transformations for handling UnivISRef Elements
//    @XmlElementWrapper(name="contacts")
//    @XmlElement(name="Contact")
//    @XmlIDREF
//    List<Person> contacts;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

}
