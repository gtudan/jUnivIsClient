package de.unibamberg.itfs.univis.domain;


import java.util.List;
import java.util.StringTokenizer;
import javax.xml.bind.annotation.*;

/**
 *
 * @author gtudan
 */
@XmlRootElement(name="Lecture")
@XmlAccessorType(value=XmlAccessType.FIELD)
public class Lecture extends UnivIsEntity {

    private Title classification;

    @XmlElementWrapper(name="dozs")
    @XmlElement(name="doz")
    private List<Doz> lecturers;

    @XmlTransient
    private boolean guestAuditors;

    private String literature;
    private String name;
    private String origin;
    private String summary;
    private int sws;
    private List<Term> terms;
    private String type;

    public Title getClassification() {
        return classification;
    }

    public void setClassification(Title classification) {
        this.classification = classification;
    }

    public boolean isGuestAuditors() {
        return guestAuditors;
    }

    /**
     * return guestAuditors as String
     * only used internally by JAXB, since univis returns booleans as "ja"/"nein"
     * 
     * @return String representation of boolean guestAuditors
     */
    @XmlElement(name="gasth", type=String.class)
    private String getGuestAuditors() {
        return String.valueOf(this.guestAuditors);
    }

    public void setGuestAuditors(boolean guestAuditors) {
        this.guestAuditors = guestAuditors;
    }

    /**
     * set guestAuditors by String
     * only used internally by JAXB, since univis returns booleans as "ja"/"nein"
     * 
     * @param guestAuditors String representation of boolean value (true/false) or (ja/nein)
     */
    private void setGuestAuditors(String guestAuditors) {
        if (guestAuditors.equalsIgnoreCase("ja")) {
            this.guestAuditors = true;
        } else {
            this.guestAuditors = Boolean.parseBoolean(guestAuditors);
        }
    }

    public List<Doz> getLecturers() {
        return lecturers;
    }

    public void setLecturers(List<Doz> lecturers) {
        this.lecturers = lecturers;
    }

    public String getLiterature() {
        return literature;
    }

    public void setLiterature(String literature) {
        this.literature = literature;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public int getSws() {
        return sws;
    }

    public void setSws(int sws) {
        this.sws = sws;
    }

    public List<Term> getTerms() {
        return terms;
    }

    public void setTerms(List<Term> terms) {
        this.terms = terms;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
