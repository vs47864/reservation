package de.fraunhofer.iosb.entity;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.net.URI;
import java.util.*;

@Entity
public class Room {

    @Override
    public String toString() {
        return roomID;
    }

    @Id
    public String roomID;

    @ElementCollection(targetClass=String.class)
    public Set<String> bleIds = new HashSet<>();

    @ElementCollection(targetClass=Long.class)
    public Map<String, Long> bleDataStream= new HashMap<>();

    public Boolean occupied = false;

    public String name;

    public String token;

    @OneToMany(mappedBy="curentRoom")
    private final List<User> curentUsers = new ArrayList<User>();

    @OneToMany(mappedBy="room")
    private final List<Term> terms = new ArrayList<Term>();

    public Room(){}

    public Room(String roomID, String name, String token)
    {
        this.roomID = roomID;
        this.name = name;
        this.token = token;
    }

    public Room(String name, String token) {
        this.name = name;
        this.token = token;
    }

    public String getRoomID() {
        return roomID;
    }
    public void setRoomID(String roomID) {
        this.roomID = roomID;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }
    public Boolean getOccupied() {
        return occupied;
    }
    public void setOccupied(Boolean occupied) {
        this.occupied = occupied;
    }
    public List<User> getCurentUsers() {
        return curentUsers;
    }
    public List<Term> getTerms() {
        return terms;
    }

    public Set<String> getBleIds() {
        return bleIds;
    }

    public void setBleIds(Set<String> bleIds) {
        this.bleIds = bleIds;
    }

    public Map<String, Long> getBleDataStream() {
        return bleDataStream;
    }

    public void setBleDataStream(Map<String, Long> bleDataStream) {
        this.bleDataStream = bleDataStream;
    }
}
