package de.fraunhofer.iosb.repository;

import de.fraunhofer.iosb.entity.Room;
import org.springframework.data.repository.CrudRepository;

public interface RoomRepository extends CrudRepository<Room, String>
{
}
