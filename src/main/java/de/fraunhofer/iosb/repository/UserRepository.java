package de.fraunhofer.iosb.repository;

import de.fraunhofer.iosb.entity.Room;
import de.fraunhofer.iosb.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, String>
{
    List<User> findUsersByFavoritesIsContaining(String key, Room room);
    User findByUsername(String username);
    List<User> findByCurentRoom(Room room);
    User findByToken(String token);
    User findByNfccode(String NFCCode);
    List<User> findUsersByNameContainingIgnoreCase(String query);
}
