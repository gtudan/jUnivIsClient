package de.unibamberg.itfs.univis.domain;

import java.util.ArrayList;
import java.util.Collections;
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
    private String address;

    @XmlElement
    private String description;

    @XmlElement(name="short")
    private String shortName;

    @XmlElement
    private String name;

    @XmlElement
    private int size;

//  This requires XML-Transformations for handling UnivISRef Elements
    @XmlElementWrapper(name="contacts")
    @XmlElement(name="contact")
    private List<Contact> contacts;

    public List<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }

    /**
     * Convenience Method for unwrapping contacts
     * @return an unmodifieable list of persons
     */
    public List<Person> getContactPersons() {
        List<Person> pl = new ArrayList<Person>();
        for (Contact c : contacts){
            pl.add(c.getPerson());
        }
        return Collections.unmodifiableList(pl);
    }

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
