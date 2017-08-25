package de.fraunhofer.iosb.representation;

import java.util.Date;

public class TermsResponse
{
    private Date startDate;

    private Date endDate;

    private String location;

    private String description;

    private String roomId;

    public TermsResponse() {
    }

    public TermsResponse(Date startDate, Date endDate, String location, String description, String roomId) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.location = location;
        this.description = description;
        this.roomId = roomId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }
}
