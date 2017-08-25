package de.fraunhofer.iosb.representation;

import java.util.Date;
import java.util.List;

public class TermDetailsResponse extends TermsResponse {
    private UserRepresentation initilaizer;

    private List<UserRepresentation> users;

    public TermDetailsResponse() {
    }

    public TermDetailsResponse(Date startDate, Date endDate, String location, String description, String roomId,UserRepresentation initilaizer, List<UserRepresentation> users) {
        super(startDate, endDate, location, description, roomId);
        this.initilaizer = initilaizer;
        this.users = users;
    }

    public TermDetailsResponse(TermsResponse term, UserRepresentation initilaizer, List<UserRepresentation> users)
    {
        super(term.getStartDate(), term.getEndDate(), term.getLocation(), term.getDescription(), term.getRoomId());
        this.initilaizer = initilaizer;
        this.users = users;
    }

    public UserRepresentation getInitilaizer() {
        return initilaizer;
    }

    public void setInitilaizer(UserRepresentation initilaizer) {
        this.initilaizer = initilaizer;
    }

    public List<UserRepresentation> getUsers() {
        return users;
    }

    public void setUsers(List<UserRepresentation> users) {
        this.users = users;
    }
}
