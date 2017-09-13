package de.fraunhofer.iosb.services;

import de.fraunhofer.iosb.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface LoginService extends UserDetailsService
{
    boolean checkUserNameAndPassword(String username, String password);

    User getUserFromToken(String authToken);

    String createToken(String username);

    boolean checkIfAdmin(String username);
}