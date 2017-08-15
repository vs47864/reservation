package de.fraunhofer.iosb.entity;

import de.fraunhofer.iosb.entity.key.TermId;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Term
{
    @EmbeddedId
    private TermId termID;

    @ManyToOne(optional=false)
    @MapsId("roomId")
    @JoinColumn(name = "RoomID")
    private Room room;

    public Term(Date from, Date till) {
        termID = new TermId();
    }

    public TermId getTermID() {
        return termID;
    }

    public void setTermID(TermId termID) {
        this.termID = termID;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }
}
