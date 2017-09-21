package de.fraunhofer.iosb.representation;

import java.util.Date;

/**
 * Created by sakovi on 19.09.2017.
 */

public class QuickRoomReservationRequest
{
    private String NFCCode;

    private long startTime;

    private long endTime;

    private String title;

    public QuickRoomReservationRequest() {
    }

    public QuickRoomReservationRequest(String NFCCode, long startTime, long endTime, String title) {
        this.NFCCode = NFCCode;
        this.startTime = startTime;
        this.endTime = endTime;
        this.title = title;
    }

    public String getNFCCode() {
        return NFCCode;
    }

    public void setNFCCode(String NFCCode) {
        this.NFCCode = NFCCode;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
