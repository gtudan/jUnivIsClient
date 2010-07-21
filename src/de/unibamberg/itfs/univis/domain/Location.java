/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.unibamberg.itfs.univis.domain;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author gtudan
 */
@XmlRootElement(name="Location")
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

}
