package de.fraunhofer.iosb.entity.key;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Date;

@Embeddable
public class TermId implements Serializable {

    private Date startDate;

    private Date endDate;

    private String roomId;

    public TermId() {
        super();
    }

    public TermId(Date startDate, Date endDate, String roomId) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.roomId = roomId;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
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
}