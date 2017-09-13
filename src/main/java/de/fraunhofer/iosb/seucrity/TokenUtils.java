package de.fraunhofer.iosb.seucrity;

import de.fraunhofer.iosb.entity.User;
import de.fraunhofer.iosb.services.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class TokenUtils
{
    private LoginService service;

    @Autowired
    public TokenUtils(LoginService service) {
        this.service = service;
    }


    public String getUsernameFromToken(String authToken) {
        if(authToken == null)
            return null;

        User user = service.getUserFromToken(authToken);
        if(user == null)
            return null;

        return user.getUsername();
    }

    public boolean validateToken(String authToken, UserDetails userDetails) {
        // FEATURE za sada token nema valjanost to treba napraviti u budućnosti
        return true;
    }
}
