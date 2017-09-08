package de.fraunhofer.iosb.services;

import de.fraunhofer.iosb.entity.Room;
import de.fraunhofer.iosb.entity.Term;
import de.fraunhofer.iosb.entity.User;
import de.fraunhofer.iosb.representation.*;
import javafx.util.Pair;

import java.util.Date;
import java.util.List;

public interface RoomService
{
    List<RoomRepresentation> getListOfRooms(NearbyRequest request, User user);

    boolean checkIfRoomIsAvailable(String id, Date startTime, Date endTime);

    Pair<Date, Date> parseDates(String startTime, String endTime, String date);

    Room findRoom(String id);

    void makeRoomOcupied(String id);

    void makeRoomUnocupied(String id);

    void scheduleOccupation(String id, Date startTime, Date endTime);

    Term getNextTerm(String id);

    Term getCurentTerm(String id);

    List<UserRepresentation> getQueryResponse(String query);

    RoomDetailsRepresentation getRoomDetails(String query, String username);
}
