package de.fraunhofer.iosb.representation;

public class ReservationResponse
{
    private boolean success = false;

    public ReservationResponse(boolean success)
    {
        this.success = success;
    }

    public ReservationResponse() {
    }

    public boolean isSuccess()
    {
        return success;
    }

    public void setSuccess(boolean success)
    {
        this.success = success;
    }
}