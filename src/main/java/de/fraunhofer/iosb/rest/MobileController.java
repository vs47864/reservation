package de.fraunhofer.iosb.rest;

import de.fraunhofer.iosb.entity.Room;
import de.fraunhofer.iosb.entity.User;
import de.fraunhofer.iosb.representation.*;
import de.fraunhofer.iosb.services.LoginService;
import de.fraunhofer.iosb.services.RoomService;
import de.fraunhofer.iosb.services.TermService;
import de.fraunhofer.iosb.services.UserService;
import de.fraunhofer.iosb.seucrity.UnauthorizedException;
import javafx.util.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/mobile")
public class MobileController
{
    private static final Logger LOG = LoggerFactory.getLogger(MobileController.class);


    private LoginService loginService;

    private RoomService roomService;

    private UserService userService;

    private TermService termService;

    @Autowired
    public MobileController(LoginService loginService, RoomService roomService, UserService userService, TermService termService)
    {
        this.userService = userService;
        this.loginService = loginService;
        this.roomService = roomService;
        this.termService = termService;
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
    public List<RoomRepresentation> nearbyRooms(@RequestBody NearbyRequest nearbyRequest, Principal principal)
    {
        User user = userService.findUser(principal.getName());
        return roomService.getListOfRooms(nearbyRequest, user);
    }

    @RequestMapping(value = "/favorites", method = RequestMethod.GET)
    public List<RoomRepresentation> favoriteRooms(Principal principal)
    {
        return userService.getFavoriteRoom(principal.getName());
    }

    @RequestMapping(value = "/favorites/terms", method = RequestMethod.GET)
    public List<TermsResponse> favoriteRoomsTerms(Principal principal)
    {
        return termService.getFavoriteRoomTerms(principal.getName());
    }

    @RequestMapping(value = "/room/{id}", method = RequestMethod.POST)
    public ReservationResponse roomDetails(@PathVariable(value="id") String id,
                                                 Principal principal, @RequestBody ReserveRequest reserveRequest)
    {
        ReservationResponse reservationResponse = new ReservationResponse();
        Pair<Date, Date> dates = roomService.parseDates(reserveRequest.getStartTime(), reserveRequest.getEndTime(),
                                                        reserveRequest.getDate());
        if(roomService.checkIfRoomIsAvailable(id, dates.getKey(), dates.getValue()))
        {
            List<User> users = userService.getUsersByIds(reserveRequest.getUsers());
            User user = userService.findUser(principal.getName());
            Room room = roomService.findRoom(id);
            termService.addTerm(user, users, room, dates.getKey(), dates.getValue(), reserveRequest.getTitle());
            reservationResponse.setSuccess(true);
            users.add(user);
            userService.scheduleCurrentRoom(users, room, dates.getKey(), dates.getValue());
        }
        return reservationResponse;
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public List<UserRepresentation> users()
    {
        return userService.getAllUsersInRepresentation();
    }

    @RequestMapping(value = "/room/favorite/{id}", method = RequestMethod.GET)
    public ReservationResponse makeFavorite(@PathVariable(value="id") String id, Principal principal)
    {
        User user = userService.findUser(principal.getName());
        ReservationResponse reservationResponse = new ReservationResponse();
        userService.makeFavorite(id, user);
        return reservationResponse;
    }
}