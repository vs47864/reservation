package de.fraunhofer.iosb.services.impl;

import de.fraunhofer.iosb.entity.Room;
import de.fraunhofer.iosb.entity.key.TermId;
import de.fraunhofer.iosb.repository.RoomRepository;
import de.fraunhofer.iosb.repository.TermRepository;
import de.fraunhofer.iosb.representation.NearbyRequest;
import de.fraunhofer.iosb.representation.RoomRepresentation;
import de.fraunhofer.iosb.services.RoomService;
import javafx.util.Pair;
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

    @Autowired
    private TermRepository termRepository;

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

    public Pair<Date, Date> parseDates(String startTime, String endTime, String date)
    {
        String[] dateSeparate = date.split("\\.");
        int year = Integer.parseInt(dateSeparate[2])-1900;
        int month = Integer.parseInt(dateSeparate[1])-1;
        int day = Integer.parseInt(dateSeparate[0]);
        String[] startTimeSeparate = startTime.split(":");
        int hourS = Integer.parseInt(startTimeSeparate[0]);
        int minuteS = Integer.parseInt(startTimeSeparate[1]);
        Date date1 = new Date(year, month, day, hourS, minuteS, 0);
        Date date2 = new Date(year, month, day, hourS, minuteS, 0);
        return new Pair<>(date1, date2);
    }

    @Override
    public boolean checkIfRoomIsAvailable(String id, Date startTime, Date endTime)
    {
        if (termRepository.findOne(new TermId(startTime, endTime, id)) != null){
            return false;
        }
        else
        {
            Room tmp = roomRepository.findByRoomID(id);
            tmp.setOccupied(true);
            roomRepository.save(tmp);
            return true;
        }
    }
}
