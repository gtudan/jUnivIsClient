package de.unibamberg.itfs.univis.domain;

import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author gtudan
 */
@XmlRootElement(name = "Person")
@XmlAccessorType(XmlAccessType.NONE)
public class Person extends UnivIsEntity {

    @XmlElement(name = "firstname")
    private String firstName;

    @XmlElement(name = "lastname")
    private String lastName;

    public String getLecturerType() {
        return lecturerType;
    }

    public void setLecturerType(String lecturerType) {
        this.lecturerType = lecturerType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @XmlElement
    private String title;
    
    private boolean lecturerer;

    @XmlElement(name="lehrtyp")
    private String lecturerType;

    private boolean visible;

    @XmlElementWrapper(name = "locations")
    @XmlElement(name = "location", type = Location.class)
    private List<Location> locations;

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

    /**
     * This works around univis representing booleans as "ja/nein"
     */
    private String getVisible() {
        return String.valueOf(this.visible);
    }

    /**
     * This works around univis representing booleans as "ja/nein"
     */
    @XmlElement(name = "visible")
    private void setVisible(String visible) {
        if (visible.equalsIgnoreCase("ja")) {
            this.visible = true;
        } else {
            this.visible = Boolean.valueOf(visible);
        }
    }

        /**
     * This works around univis representing booleans as "ja/nein"
     */
    private String getLecturer() {
        return String.valueOf(this.lecturerer);
    }

    /**
     * This works around univis representing booleans as "ja/nein"
     */
    @XmlElement(name = "lehr")
    private void setLecturer(String lecturer) {
        if (lecturer.equalsIgnoreCase("ja")) {
            this.lecturerer = true;
        } else {
            this.lecturerer = Boolean.valueOf(lecturer);
        }
    }
}
