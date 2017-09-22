package de.fraunhofer.iosb.representation;

import java.util.ArrayList;

public class NearbyRequest
{

    private ArrayList<NearbyRoom> ids;

    public NearbyRequest(ArrayList<NearbyRoom> ids) {
        this.ids = ids;
    }

    public NearbyRequest() {
    }

    @Override
    public String toString() {
        return "NearbyRequest{" +
                "ids=" + ids +
                '}';
    }

    public ArrayList<NearbyRoom> getIds() {
        return ids;
    }

    public void setIds(ArrayList<NearbyRoom> ids) {
        this.ids = ids;
    }
}
