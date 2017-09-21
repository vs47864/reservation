package de.fraunhofer.iosb.representation;

import java.util.Date;

public class RoomRepresentation
{
    public RoomRepresentation() {}

    private String name;

    private String roomID;

    private boolean occupied;

    private boolean favorite = false;

    private Date from;

    private Date until;

    public RoomRepresentation(String roomID, String name, boolean occupied, Date from, Date until, boolean favorite)
    {
        this.roomID = roomID;
        this.name = name;
        this.occupied = occupied;
        this.from = from;
        this.until = until;
        this.favorite = favorite;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
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

    public Date getUntil() {
        return until;
    }

    public void setUntil(Date until) {
        this.until = until;
    }
}
