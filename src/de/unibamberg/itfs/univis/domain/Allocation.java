package de.unibamberg.itfs.univis.domain;

import de.unibamberg.itfs.univis.util.DateParser;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.annotation.*;

/**
 *
 * @author gtudan
 */
@XmlRootElement(name = "Allocation")
@XmlAccessorType(XmlAccessType.FIELD)
public class Allocation extends UnivIsEntity {

    @XmlElement
    private String title;
    @XmlElement
    private Contact contact;
    @XmlTransient
    private Date endDate;
    @XmlTransient
    private Date startDate;
    @XmlElementWrapper(name = "rooms")
    @XmlElement(name = "room")
    private List<RoomEntry> rooms;

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public List<RoomEntry> getRooms() {
        return rooms;
    }

    public void setRooms(List<RoomEntry> rooms) {
        this.rooms = rooms;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @XmlElement(name = "startdate")
    private void setStartdate(String dateString) {
        GregorianCalendar cal = new GregorianCalendar();

        if (this.startDate != null) {
            cal.setTime(this.startDate);
        }

        try {
            Date newDate = DateParser.stringToDate(dateString);
            GregorianCalendar newCal = new GregorianCalendar();
            newCal.setTime(newDate);
            cal.set(newCal.get(Calendar.YEAR), newCal.get(Calendar.MONTH), newCal.get(Calendar.DAY_OF_MONTH));

            this.startDate = cal.getTime();

        } catch (ParseException ex) {
            Logger.getLogger(Allocation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private String getStartdate() {
        return DateParser.dateToString(this.startDate);
    }

    @XmlElement(name = "starttime")
    private void setStarttime(String timeString) {
        GregorianCalendar cal = new GregorianCalendar();

        if (this.startDate != null) {
            cal.setTime(this.startDate);
        } else {
            cal.setTime(new Date());
        }

        try {
            Date newDate = DateParser.stringToTime(timeString);
            GregorianCalendar newCal = new GregorianCalendar();
            newCal.setTime(newDate);

            cal.set(
                    cal.get(Calendar.YEAR),
                    cal.get(Calendar.MONTH),
                    cal.get(Calendar.DAY_OF_MONTH),
                    newCal.get(Calendar.HOUR_OF_DAY),
                    newCal.get(Calendar.MINUTE));

            this.startDate = cal.getTime();

        } catch (ParseException ex) {
            Logger.getLogger(Allocation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private String getStarttime() {
        return DateParser.timeToString(this.startDate);
    }

    @XmlElement(name = "enddate")
    private void setEnddate(String dateString) {
        GregorianCalendar cal = new GregorianCalendar();

        if (this.endDate != null) {
            cal.setTime(this.endDate);
        }

        try {
            Date newDate = DateParser.stringToDate(dateString);
            GregorianCalendar newCal = new GregorianCalendar();
            newCal.setTime(newDate);
            cal.set(newCal.get(Calendar.YEAR), newCal.get(Calendar.MONTH), newCal.get(Calendar.DAY_OF_MONTH));

            this.endDate = cal.getTime();
        } catch (ParseException ex) {
            Logger.getLogger(Allocation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private String getEnddate() {
        return DateParser.dateToString(this.endDate);
    }

    @XmlElement(name = "endtime")
    private void setEndtime(String timeString) {
        GregorianCalendar cal = new GregorianCalendar();

        if (this.endDate != null) {
            cal.setTime(this.endDate);
        } else {
            cal.setTime(new Date());
        }

        try {
            Date newDate = DateParser.stringToTime(timeString);
            GregorianCalendar newCal = new GregorianCalendar();
            newCal.setTime(newDate);

            cal.set(
                    cal.get(Calendar.YEAR),
                    cal.get(Calendar.MONTH),
                    cal.get(Calendar.DAY_OF_MONTH),
                    newCal.get(Calendar.HOUR_OF_DAY),
                    newCal.get(Calendar.MINUTE));

            this.endDate = cal.getTime();

        } catch (ParseException ex) {
            Logger.getLogger(Allocation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private String getEndtime() {
        return DateParser.timeToString(this.endDate);
    }
}
