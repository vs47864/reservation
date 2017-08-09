package de.fraunhofer.iosb.services.impl;

import de.fraunhofer.iosb.entity.User;
import de.fraunhofer.iosb.repository.UserRepository;
import de.fraunhofer.iosb.services.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class LoginServiceImplementation implements LoginService
{
    @Autowired
    private UserRepository repo;

    public boolean checkUserNameAndPassword(String username, String password)
    {
        User user = repo.findByUsername(username);
        if(user == null)
            return false;

        if(!(user.getPassword().equals(password)))
            return false;

        return true;
    }

    @Override
    public User getUserFromToken(String authToken)
    {
        return repo.findByToken(authToken);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        //TODO
        return null;
    }

    @Override
    @Transactional
    public String createToken(String username)
    {
        String token = UUID.randomUUID().toString();

        User user = repo.findByUsername(username);
        while(repo.findByToken(token) != null)
            token = UUID.randomUUID().toString();

        user.setToken(token);
        repo.save(user);

        return token;
    }
}
