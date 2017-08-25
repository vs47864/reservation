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

    private String title;

    @ManyToOne(optional=false)
    @MapsId("roomId")
    @JoinColumn(name = "RoomID")
    private Room room;


    @ManyToOne(optional=false)
    @JoinColumn(name = "Username")
    private User user;

    @ManyToMany
    private List<User> users = new ArrayList<>();

    public Term(TermId termID, String title, Room room, User user, List<User> users) {
        this.termID = termID;
        this.title = title;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
