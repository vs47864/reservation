package de.fraunhofer.iosb.services;

import de.fraunhofer.iosb.entity.Room;
import de.fraunhofer.iosb.entity.User;
import de.fraunhofer.iosb.representation.TermDetailsResponse;
import de.fraunhofer.iosb.representation.TermsResponse;

import java.util.Date;
import java.util.List;

public interface TermService
{
    void addTerm(User user, List<User> users, Room room, Date from, Date untill, String title);

    List<TermsResponse> getFavoriteRoomTerms(String username);

    TermDetailsResponse getTerm(TermsResponse term);
}
