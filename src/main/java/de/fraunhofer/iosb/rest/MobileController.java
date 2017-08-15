package de.fraunhofer.iosb.rest;


import de.fraunhofer.iosb.representation.*;
import de.fraunhofer.iosb.services.LoginService;
import de.fraunhofer.iosb.services.RoomService;
import de.fraunhofer.iosb.services.UserService;
import de.fraunhofer.iosb.seucrity.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping(value = "/mobile")
public class MobileController
{
    private static final Logger LOG = LoggerFactory.getLogger(MobileController.class);


    private LoginService loginService;

    private RoomService roomService;

    private UserService userService;

    @Autowired
    public MobileController(LoginService loginService, RoomService roomService, UserService userService)
    {
        this.userService = userService;
        this.loginService = loginService;
        this.roomService = roomService;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public TokenRepresentation login(@Validated @RequestBody UserCredentialRepresentation userCredential)
    {
        LOG.debug("Received at /login {}", userCredential);
        boolean checked = loginService.checkUserNameAndPassword(
                userCredential.getUsername(),
                userCredential.getPassword());

        if(checked)
        {
            TokenRepresentation tokenRepresentation = new TokenRepresentation(loginService.createToken(userCredential.getUsername()));
            LOG.debug("Returns at /login {}", tokenRepresentation);
            return tokenRepresentation;
        } else
            {
            LOG.debug("Returns at /login UnauthorizedEception");
            throw new UnauthorizedException();
        }
    }

    @RequestMapping(value = "/nearby", method = RequestMethod.POST)
    public List<RoomRepresentation> nearbyRooms(@RequestBody NearbyRequest nearbyRequest)
    {
        return roomService.getListOfRooms(nearbyRequest);
    }

    @RequestMapping(value = "/room/{id}", method = RequestMethod.POST)
    public RoomDetailsRepresentation roomDetails(@PathVariable(value="id") String id, Principal principal)
    {
        return null;
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public List<UserRepresentation> users()
    {
        return userService.getAllUsersInRepresentation();
    }
}