package de.fraunhofer.iosb.entity;

import de.fraunhofer.iosb.entity.key.TermId;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Term
{
    @EmbeddedId
    TermId termID;

    private Date from;

    private Date till;

    @ManyToOne(optional=false)
    @MapsId("roomId")
    private Room room;

    @ManyToOne(optional=false)
    @MapsId("userName")
    private User user;

    public Term(Date from, Date till) {
        termID = new TermId();
        this.from = from;
        this.till = till;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getFrom() {
        return from;
    }

    public void setFrom(Date from) {
        this.from = from;
    }

    public Date getTill() {
        return till;
    }

    public void setTill(Date till) {
        this.till = till;
    }
}
