package de.fraunhofer.iosb.services.impl;

import de.fraunhofer.iosb.repository.RoomRepository;
import de.fraunhofer.iosb.representation.NearbyRequest;
import de.fraunhofer.iosb.representation.RoomRepresentation;
import de.fraunhofer.iosb.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

        }
        return null;
    }
}
