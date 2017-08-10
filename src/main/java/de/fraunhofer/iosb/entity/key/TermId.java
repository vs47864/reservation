package de.fraunhofer.iosb.entity.key;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class TermId implements Serializable {

    private String userName;
    private String roomId;

    public TermId() {
        super();
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}