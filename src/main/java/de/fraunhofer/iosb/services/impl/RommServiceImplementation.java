package de.fraunhofer.iosb.services.impl;

import de.fraunhofer.iosb.entity.Room;
import de.fraunhofer.iosb.entity.Term;
import de.fraunhofer.iosb.entity.User;
import de.fraunhofer.iosb.repository.RoomRepository;
import de.fraunhofer.iosb.repository.TermRepository;
import de.fraunhofer.iosb.representation.NearbyRequest;
import de.fraunhofer.iosb.representation.RoomRepresentation;
import de.fraunhofer.iosb.services.RoomService;
import javafx.util.Pair;
import org.joda.time.Interval;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RommServiceImplementation implements RoomService
{
    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private TermRepository termRepository;

    @Override
    public List<RoomRepresentation> getListOfRooms(NearbyRequest request, User user)
    {
        List<RoomRepresentation> result = new ArrayList<>();
        for (String id: request.getIds())
        {
            boolean favorite = user.getFavorites().containsKey(id);
            Room room = roomRepository.findByRoomID(id);
            RoomRepresentation representation = new RoomRepresentation(room.roomID, room.name, room.occupied, new Date(), new Date(), favorite);
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

        String[] endTimeSeparate = endTime.split(":");
        int hourE = Integer.parseInt(endTimeSeparate[0]);
        int minuteE = Integer.parseInt(endTimeSeparate[1]);
        Date date2 = new Date(year, month, day, hourE, minuteE, 0);

        return new Pair<>(date1, date2);
    }

    @Override
    public Room findRoom(String id) {
        return roomRepository.findByRoomID(id);
    }

    @Override
    public void makeRoomOcupied(String id)
    {
        Room room = roomRepository.findByRoomID(id);
        room.setOccupied(true);
        roomRepository.save(room);
    }

    @Override
    public void makeRoomUnocupied(String id)
    {
        Room room = roomRepository.findByRoomID(id);
        room.setOccupied(false);
        roomRepository.save(room);
    }

    @Override
    public boolean checkIfRoomIsAvailable(String id, Date startTime, Date endTime)
    {
        Room room = roomRepository.findByRoomID(id);
        List<Term> terms= room.getTerms();
        if (terms != null)
        {
            for (Term term : terms)
            {
               Interval interval1 = new Interval(startTime.getTime(), endTime.getTime());
               Interval interval2 = new Interval(term.getTermID().getStartDate().getTime(), term.getTermID().getEndDate().getTime());
               if (interval1.overlaps(interval2))
               {
                   return false;
               }
            }
        }
        scheduleOccupation(id, startTime, endTime);
        return true;
    }

    public void scheduleOccupation(String id ,Date startTime, Date endTime)
    {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                makeRoomOcupied(id);
            }
        }, startTime);

        timer.schedule(new TimerTask() {
            public void run() {
                makeRoomUnocupied(id);
            }
        }, endTime);
    }

}
