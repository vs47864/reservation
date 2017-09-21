package de.fraunhofer.iosb.representation;

import java.util.Date;
import java.util.List;

public class TermDetailsResponse extends TermsResponse
{
    private UserRepresentation initializer;

    private List<UserRepresentation> users;

    public TermDetailsResponse() {
    }

    public TermDetailsResponse(Date startDate, Date endDate, String location, String description, String roomId,UserRepresentation initializer, List<UserRepresentation> users) {
        super(startDate, endDate, location, description, roomId);
        this.initializer = initializer;
        this.users = users;
    }

    public TermDetailsResponse(TermsResponse term, UserRepresentation initializer, List<UserRepresentation> users)
    {
        super(term.getStartDate(), term.getEndDate(), term.getLocation(), term.getDescription(), term.getRoomId());
        this.initializer = initializer;
        this.users = users;
    }

    public UserRepresentation getInitializer() {
        return initializer;
    }

    public void setInitializer(UserRepresentation initializer) {
        this.initializer = initializer;
    }

    public List<UserRepresentation> getUsers() {
        return users;
    }

    public void setUsers(List<UserRepresentation> users) {
        this.users = users;
    }
}
