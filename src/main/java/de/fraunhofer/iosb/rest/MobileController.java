package de.fraunhofer.iosb.rest;


import de.fraunhofer.iosb.representation.*;
import de.fraunhofer.iosb.services.LoginService;
import de.fraunhofer.iosb.services.RoomService;
import de.fraunhofer.iosb.seucrity.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/mobile")
public class MobileController
{
    private static final Logger LOG = LoggerFactory.getLogger(MobileController.class);


    private LoginService loginService;

    private RoomService roomService;

    @Autowired
    public MobileController(LoginService loginService, RoomService roomService)
    {
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

    @RequestMapping(value = "/room/{id}", method = RequestMethod.GET)
    public RoomDetailsRepresentation roomDetails(@PathVariable(value="id") String id)
    {
        return null;
    }
}