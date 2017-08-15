package de.fraunhofer.iosb.services.impl;

import de.fraunhofer.iosb.entity.User;
import de.fraunhofer.iosb.repository.UserRepository;
import de.fraunhofer.iosb.representation.UserRepresentation;
import de.fraunhofer.iosb.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImplementation implements UserService
{
    @Autowired
    private UserRepository repo;

    @Override
    public List<UserRepresentation> getAllUsersInRepresentation()
    {
        List<UserRepresentation> userRepresentations = new ArrayList<>();

        for (User user : repo.findAll())
        {
            UserRepresentation userRepresentation = new UserRepresentation(
                    user.getLastname()+" "+user.getName(), user.getUsername());
            userRepresentations.add(userRepresentation);
        }
        return userRepresentations;
    }
}
