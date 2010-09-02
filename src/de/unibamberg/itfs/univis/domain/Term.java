package de.unibamberg.itfs.univis.domain;

import de.unibamberg.itfs.univis.util.DateParser;
import java.text.ParseException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.annotation.*;

/**
 * The strange univis representation showing when a lecture takes place
 * You may find it easier to use the allocation class instead.
 *
 * Basically, you get a start-date, an enddate an a repeat intervall.
 * Time and date have to be kept seperately, since the date is optional.
 *
 * @author gtudan
 */
@XmlRootElement(name = "term")
@XmlAccessorType(XmlAccessType.NONE)
public class Term {

    @XmlElement(name="room")
    RoomEntry room;
    Date endTime;
    Date startTime;
    Date startDate;
    Date endDate;
    @XmlElement(name = "repeat")
    String repeat;
    @XmlElement(name = "exclude")
    String exclude;

    @XmlElement(name="starttime")
    private void setStartTimeString(String startTime) {
        try {
            this.startTime = DateParser.stringToTime(startTime);
        } catch (ParseException ex) {
            Logger.getLogger(Term.class.getName()).log(Level.SEVERE, "Could not parse startTime:" + startTime, ex);
        }
    }

    @XmlElement(name="endtime")
    private void setEndTimeString(String endTime) {
        try {
            this.endTime = DateParser.stringToTime(endTime);
        } catch (ParseException ex) {
            Logger.getLogger(Term.class.getName()).log(Level.SEVERE, "Could not parse endTime:" + startTime, ex);
        }
    }

    private String getStartTimeString() {
        return DateParser.timeToString(this.startTime);
    }

    private String getEndTimeString() {
        return DateParser.timeToString(this.endTime);
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
            Logger.getLogger(Term.class.getName()).log(Level.SEVERE, "Could not parse endTime:" + startDate, ex);
        }
    }

    private String getStartDateString() {
        return DateParser.dateToString(this.startDate);
    }

    private String getEndDateString() {
        return DateParser.dateToString(this.endDate);
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getExclude() {
        return exclude;
    }

    public void setExclude(String exclude) {
        this.exclude = exclude;
    }

    public String getRepeat() {
        return repeat;
    }

    public void setRepeat(String repeat) {
        this.repeat = repeat;
    }

    public Room getRoom() {
    	if (room != null) {
        	return room.getRoom();
        } else return null;
    }

    public void setRoom(Room room) {
        RoomEntry roomEntry = new RoomEntry();
        roomEntry.setRoom(room);
        this.room = roomEntry;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
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
