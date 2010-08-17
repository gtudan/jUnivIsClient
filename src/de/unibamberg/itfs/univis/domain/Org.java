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
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Org extends UnivIsEntity{
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

}
