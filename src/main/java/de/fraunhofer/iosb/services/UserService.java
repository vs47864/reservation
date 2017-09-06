package de.fraunhofer.iosb.services;

import de.fraunhofer.iosb.entity.Room;
import de.fraunhofer.iosb.entity.User;
import de.fraunhofer.iosb.representation.RoomRepresentation;
import de.fraunhofer.iosb.representation.TermsResponse;
import de.fraunhofer.iosb.representation.UserRepresentation;

import java.util.Date;
import java.util.List;

public interface UserService
{
    List<UserRepresentation> getAllUsersInRepresentation(User user1);

    List<User> getUsersByIds(List<String> ids);

    User findUser(String name);

    void scheduleCurrentRoom(List<User> users, Room room, Date startTime, Date endTime);

    void setCurrentRoom(Room room, List<User> users);

    void unSetCurrentRoom(List<User> users);

    void makeFavorite(String roomId, User user);

    List<RoomRepresentation> getFavoriteRoom(String username);

    List<TermsResponse> getTerms(String username);
}
