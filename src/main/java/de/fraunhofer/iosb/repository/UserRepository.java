package de.fraunhofer.iosb.repository;

import de.fraunhofer.iosb.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, String>
{
    User findByUsername(String username);
    User findByToken(String token);
    User findByNfccode(String NFCCode);

    List<User> findUsersByNameContainingIgnoreCaseOrLastnameContainingIgnoreCase(String query, String query1);
}
