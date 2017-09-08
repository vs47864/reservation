package de.fraunhofer.iosb.representation;

import java.util.ArrayList;
import java.util.List;

public class RoomDetailsRepresentation
{
    private boolean favorite;

    private boolean occupied;

    private List<TermsResponse> terms = new ArrayList<>();

    public RoomDetailsRepresentation(){}

    public RoomDetailsRepresentation(boolean favorite, boolean occupied, List<TermsResponse> terms) {
        this.favorite = favorite;
        this.occupied = occupied;
        this.terms = terms;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }

    public void setTerms(List<TermsResponse> terms) {
        this.terms = terms;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public boolean isOccupied() {
        return occupied;
    }

    public List<TermsResponse> getTerms() {
        return terms;
    }
}
