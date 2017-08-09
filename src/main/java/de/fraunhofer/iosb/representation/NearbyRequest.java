package de.fraunhofer.iosb.representation;

import java.util.ArrayList;

public class NearbyRequest
{

    private ArrayList<String> ids;

    public NearbyRequest(ArrayList<String> ids) {
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

    public ArrayList<String> getIds() {
        return ids;
    }

    public void setIds(ArrayList<String> ids) {
        this.ids = ids;
    }
}
