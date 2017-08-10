package de.fraunhofer.iosb.services.impl;

import de.fraunhofer.iosb.entity.Room;
import de.fraunhofer.iosb.repository.RoomRepository;
import de.fraunhofer.iosb.representation.NearbyRequest;
import de.fraunhofer.iosb.representation.RoomRepresentation;
import de.fraunhofer.iosb.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class RommServiceImplementation implements RoomService
{
    @Autowired
    private RoomRepository roomRepository;

    @Override
    public List<RoomRepresentation> getListOfRooms(NearbyRequest request)
    {
        List<RoomRepresentation> result = new ArrayList<>();
        for (String id: request.getIds())
        {
            Room room = roomRepository.findByRoomID(id);
            RoomRepresentation representation = new RoomRepresentation(room.roomID, room.name, room.occupied, new Date(), new Date());
            result.add(representation);
        }
        return result;
    }
}
