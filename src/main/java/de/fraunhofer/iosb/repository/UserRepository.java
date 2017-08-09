package de.fraunhofer.iosb.repository;

import de.fraunhofer.iosb.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, String>
{
    User findByUsername(String username);
    User findByToken(String token);
}
