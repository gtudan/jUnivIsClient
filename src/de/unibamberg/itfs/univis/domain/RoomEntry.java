package de.unibamberg.itfs.univis.domain;

import javax.xml.bind.annotation.*;

/**
 * Wrapper for a room
 * Used in Term
 *
 * @author gtudan
 */
@XmlRootElement(name="room")
@XmlAccessorType(XmlAccessType.FIELD)
public class RoomEntry {

    @XmlElement(name="Room")
    private Room room;

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }
}
