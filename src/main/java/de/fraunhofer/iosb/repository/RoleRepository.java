package de.fraunhofer.iosb.repository;


import de.fraunhofer.iosb.entity.Role;
import de.fraunhofer.iosb.entity.Room;
import de.fraunhofer.iosb.entity.User;
import de.fraunhofer.iosb.entity.key.RoleId;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RoleRepository extends CrudRepository<Role, RoleId>
{
    List<Role> findAllByUser(User user);
    List<Role> findAllByRoom(Room rom);
}
