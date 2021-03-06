package de.fraunhofer.iosb.services;

import de.fraunhofer.iosb.entity.Room;
import de.fraunhofer.iosb.entity.User;
import de.fraunhofer.iosb.representation.RoomRepresentation;
import de.fraunhofer.iosb.representation.TermsResponse;
import de.fraunhofer.iosb.representation.UserDetailsRepresentation;
import de.fraunhofer.iosb.representation.UserRepresentation;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

public interface UserService
{
    List<UserRepresentation> getAllUsersInRepresentation();

    List<User> getUsersByIds(List<String> ids);

    User findUser(String name);

    void scheduleCurrentRoom(List<User> users, Room room, Date startTime, Date endTime);

    void setCurrentRoom(Room room, List<User> users);

    void unSetCurrentRoom(List<User> users);

    void makeFavorite(String roomId, User user);

    List<RoomRepresentation> getFavoritesRoom(String username);

    List<TermsResponse> getTerms(String username);

    List<UserRepresentation> getQueryResponse(String query);

    UserDetailsRepresentation getUserDetails(String id);

    User getUserByNFC(String nfcCode);

    Iterable<User> findAllInRoom(String id);

    Iterable<User> findAll();

    void delete(String id);

    boolean notexists(String username);

    @Transactional
    User save(User user);

    @Transactional
    void update(User user, String id);
}
