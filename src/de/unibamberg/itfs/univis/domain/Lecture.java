package de.unibamberg.itfs.univis.domain;

import de.unibamberg.itfs.univis.util.DateParser;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.annotation.*;

/**
 *
 * @author gtudan
 */
@XmlRootElement(name = "Lecture")
@XmlAccessorType(value = XmlAccessType.NONE)
public class Lecture extends UnivIsEntity {

    ParentLecture parentLecture;

    //General description
    @XmlElement
    private String name;
    @XmlElement
    private Title classification;
    @XmlElement
    private String summary = "";
    @XmlElement
    private String comment = "";
    @XmlElement
    private String literature = "";
    @XmlElement
    private String organizational;
    @XmlElementWrapper(name = "dozs")
    @XmlElement(name = "doz")
    private List<Doz> lecturers;
    @XmlElementWrapper(name = "courses")
    @XmlElement(name = "course")
    private List<Course> courses;

    // Flags
    private boolean beginners = false;
    private boolean guestAuditors = false;
    private boolean facultative = false;
    private boolean certificate = false; //"Benoteter Schein"
    private boolean ects = false;

    //  Akkreditierungsdaten
    @XmlElement(name = "ects_cred")
    private int ectsCredits;
    @XmlElement
    private int sws;
    @XmlElement
    private int turnout;
    @XmlElement
    private String type;

    // Origin of import (Flexnow ID)
    @XmlElement
    private String origin;

    // Is this a copy of another univis lecture?
    @XmlElement
    private long importParentID;
    @XmlElement
    private List<Term> terms;

    //Private void alternative start/end dates (does not match semester)
    private Date startDate;
    private Date endDate;

    @XmlElement(name = "allfak")
    private void setFacultative(String facultative) {
        this.facultative = facultative.equalsIgnoreCase("ja") ? true : Boolean.parseBoolean(facultative);
    }

    private String getFacultatice() {
        return String.valueOf(this.facultative);
    }

    public boolean isFacultative() {
        return facultative;
    }

    public void setFacultative(boolean facultative) {
        this.facultative = facultative;
    }

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
    @XmlElement(name = "gasth", type = String.class)
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
        this.guestAuditors = guestAuditors.equalsIgnoreCase("ja") ? true : Boolean.parseBoolean(guestAuditors);
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

    @XmlElement
    private void setBeginners(String beginners) {
        this.beginners = beginners.equalsIgnoreCase("ja") ? true : Boolean.valueOf(beginners);
    }

    private String getBeginners() {
        return String.valueOf(this.beginners);
    }

    public boolean isBeginners() {
        return beginners;
    }

    public void setBeginners(boolean beginners) {
        this.beginners = beginners;
    }

    @XmlElement(name = "benschein", type = String.class)
    private void setCertificate(String cert) {
        this.certificate = cert.equalsIgnoreCase("ja") ? true : Boolean.valueOf(cert);
    }

    private String getCertificate() {
        return String.valueOf(certificate);
    }

    public boolean isCertificate() {
        return certificate;
    }

    public void setCertificate(boolean certificate) {
        this.certificate = certificate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    @XmlElement(name = "ects")
    private void setEcts(String ects) {
        this.ects = ects.equalsIgnoreCase("ja") ? true : Boolean.parseBoolean(ects);
    }

    private String getEcts() {
        return String.valueOf(this.ects);
    }

    public boolean isEcts() {
        return ects;
    }

    public void setEcts(boolean ects) {
        this.ects = ects;
    }

    public int getEctsCredits() {
        return ectsCredits;
    }

    public void setEctsCredits(int ectsCredits) {
        this.ectsCredits = ectsCredits;
    }

    public long getImportParentID() {
        return importParentID;
    }

    public void setImportParentID(long importParentID) {
        this.importParentID = importParentID;
    }

    public int getTurnout() {
        return turnout;
    }

    public void setTurnout(int turnout) {
        this.turnout = turnout;
    }

    public String getOrganizational() {
        return organizational;
    }

    public void setOrganizational(String organizational) {
        this.organizational = organizational;
    }

    @XmlElement(name = "parent-lv")
    private void setParentLectureWrapper(ParentLecture pl) {
        this.parentLecture = pl;
    }

    private ParentLecture getParentLectureWrapper() {
        return this.parentLecture;
    }

    public Lecture getParentLecture() {
        return parentLecture.lecture;
    }

    public void setParentLecture(Lecture parentLecture) {
        ParentLecture pl = new ParentLecture();
        pl.setLecture(parentLecture);
        this.parentLecture = pl;
    }

    @XmlElement(name="startdate")
    private void setStartDateString(String startTime) {
        try {
            this.startDate = DateParser.stringToDate(startTime);
        } catch (ParseException ex) {
            Logger.getLogger(Term.class.getName()).log(Level.SEVERE, "Could not parse startTime:" + startTime, ex);
        }
    }

    @XmlElement(name="enddate")
    private void setEndDateString(String endDate) {
        try {
            this.endDate = DateParser.stringToDate(endDate);
        } catch (ParseException ex) {
            Logger.getLogger(Term.class.getName()).log(Level.SEVERE, "Could not parse endTime:" + endDate, ex);
        }
    }

    private String getStartDateString() {
        return DateParser.dateToString(this.startDate);
    }

    private String getEndDateString() {
        return DateParser.dateToString(this.endDate);
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }


}
