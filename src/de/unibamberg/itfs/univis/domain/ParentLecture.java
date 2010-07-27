/*
 */

package de.unibamberg.itfs.univis.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author gtudan
 */
@XmlRootElement(name="parent-lv")
@XmlAccessorType(XmlAccessType.FIELD)
class ParentLecture {
    @XmlElement(name="Lecture")
    Lecture lecture;

    public Lecture getLecture() {
        return lecture;
    }

    public void setLecture(Lecture lecture) {
        this.lecture = lecture;
    }



}
