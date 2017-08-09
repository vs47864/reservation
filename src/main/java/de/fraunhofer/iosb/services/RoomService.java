package de.fraunhofer.iosb.services;

import de.fraunhofer.iosb.representation.NearbyRequest;
import de.fraunhofer.iosb.representation.RoomRepresentation;

import java.util.List;

public interface RoomService
{
    List<RoomRepresentation> getListOfRooms(NearbyRequest request);
}
