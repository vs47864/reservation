package de.fraunhofer.iosb.representation;

import java.util.Date;

public class RoomRepresentation
{
    public RoomRepresentation() {}

    private String name;

    private String roomID;

    private boolean occupied;

    private Date from;

    private Date untill;

    public RoomRepresentation(String roomID, String name, boolean occupied, Date from, Date untill)
    {
        this.roomID = roomID;
        this.name = name;
        this.occupied = occupied;
        this.from = from;
        this.untill = untill;
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

    public boolean isOccupied() {
        return occupied;
    }

    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }

    public Date getFrom() {
        return from;
    }

    public void setFrom(Date from) {
        this.from = from;
    }

    public Date getUntill() {
        return untill;
    }

    public void setUntill(Date untill) {
        this.untill = untill;
    }
}
