package de.fraunhofer.iosb.entity;

import de.fraunhofer.iosb.entity.key.TermId;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Term
{
    @EmbeddedId
    private TermId termID;

    @ManyToOne(optional=false)
    @MapsId("roomId")
    @JoinColumn(name = "RoomID")
    private Room room;


    @ManyToOne(optional=false)
    @JoinColumn(name = "Username")
    private User user;

    @ManyToMany
    private List<User> users = new ArrayList<>();

    public Term(TermId termID, Room room, User user, List<User> users) {
        this.termID = termID;
        this.room = room;
        this.user = user;
        this.users = users;
    }

    public Term() {
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
