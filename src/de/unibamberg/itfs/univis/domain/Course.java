/*
 */

package de.unibamberg.itfs.univis.domain;

import javax.xml.bind.annotation.*;

/**
 *
 * @author gtudan
 */
@XmlRootElement(name="Course")
@XmlAccessorType(XmlAccessType.FIELD)
public class Course {
    @XmlElement(name="Lecture")
    Lecture lecture;

    public Lecture getLecture() {
        return lecture;
    }

    public void setLecture(Lecture lecture) {
        this.lecture = lecture;
    }


}
