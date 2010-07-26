/*
 */
package de.unibamberg.itfs.univis.domain;

import javax.xml.bind.annotation.*;

/**
 *
 * @author gtudan
 */
@XmlRootElement(name = "doz")
@XmlAccessorType(XmlAccessType.FIELD)
public class Doz {

    @XmlElement(name = "Person")
    private Person person;

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
