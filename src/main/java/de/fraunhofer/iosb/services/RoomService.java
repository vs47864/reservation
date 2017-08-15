package de.fraunhofer.iosb.services;

import de.fraunhofer.iosb.representation.NearbyRequest;
import de.fraunhofer.iosb.representation.RoomRepresentation;
import javafx.util.Pair;

import java.util.Date;
import java.util.List;

public interface RoomService
{
    List<RoomRepresentation> getListOfRooms(NearbyRequest request);

    boolean checkIfRoomIsAvailable(String id, Date startTime, Date endTime);

    Pair<Date, Date> parseDates(String startTime, String endTime, String date);
}
