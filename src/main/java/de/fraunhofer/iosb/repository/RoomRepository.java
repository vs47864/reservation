package de.fraunhofer.iosb.repository;

import de.fraunhofer.iosb.entity.Room;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RoomRepository extends CrudRepository<Room, String>
{
    Room findByRoomID(String roomID);
    List<Room> findRoomsByNameContainingIgnoreCase(String query);
    Room findByBleIds(String bleId);
}
