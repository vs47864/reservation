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
import java.util.ArrayList;
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
            TokenRepresentation tokenRepresentation = new TokenRepresentation(loginService.createToken(userCredential.getUsername()),loginService.checkIfAdmin(userCredential.getUsername()));
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
                                                 Principal principal, @RequestBody ReservationRequest reservationRequest)
    {
        ReservationResponse reservationResponse = new ReservationResponse();
        Pair<Date, Date> dates = roomService.parseDates(reservationRequest.getStartTime(), reservationRequest.getEndTime(),
                                                        reservationRequest.getDate());

        User user;
        if(reservationRequest.getNfccode() != null)
        {
            user = userService.getUserByNFC(reservationRequest.getNfccode());
        }else {
            user = userService.findUser(principal.getName());
        }

        if(roomService.checkIfRoomIsAvailable(id, dates.getKey(), dates.getValue()) && user != null)
        {
            List<User> users = userService.getUsersByIds(reservationRequest.getUsers());
            Room room = roomService.findRoom(id);
            termService.addTerm(user, users, room, dates.getKey(), dates.getValue(), reservationRequest.getTitle());
            reservationResponse.setSuccess(true);
            users.add(user);
            userService.scheduleCurrentRoom(users, room, dates.getKey(), dates.getValue());
        }
        return reservationResponse;
    }

    @RequestMapping(value = "/room/quick/{id}", method = RequestMethod.POST)
    public ReservationResponse roomDetails(@PathVariable(value="id") String id, Principal principal, @RequestBody QuickRoomReservationRequest reserveRequest)
    {
        ReservationResponse reservationResponse = new ReservationResponse();
        Date startDate = new Date(reserveRequest.getStartTime());
        Date endDate = new Date(reserveRequest.getEndTime());
        User user = userService.getUserByNFC(reserveRequest.getNFCCode());

        if(user == null) return reservationResponse;

        if(roomService.checkIfRoomIsAvailable(id, startDate, endDate))
        {
            List<User> users = new ArrayList<>();
            Room room = roomService.findRoom(id);
            termService.addTerm(user, users, room,  startDate, endDate, reserveRequest.getTitle());
            reservationResponse.setSuccess(true);
            users.add(user);
            userService.scheduleCurrentRoom(users, room, startDate, endDate);
        }
        return reservationResponse;
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public List<UserRepresentation> users(Principal principal)
    {
        User user = userService.findUser(principal.getName());
        return userService.getAllUsersInRepresentation(user);
    }

    @RequestMapping(value = "/room/favorite/{id}", method = RequestMethod.GET)
    public ReservationResponse makeFavorite(@PathVariable(value="id") String id, Principal principal)
    {
        User user = userService.findUser(principal.getName());
        ReservationResponse reservationResponse = new ReservationResponse();
        userService.makeFavorite(id, user);
        return reservationResponse;
    }

    @RequestMapping(value = "/favorites/terms/", method = RequestMethod.POST)
    public TermDetailsResponse termDetailsResponse(@RequestBody TermsResponse term)
    {
        return termService.getTerm(term);
    }

    @RequestMapping(value = "/users/terms", method = RequestMethod.GET)
    public List<TermsResponse> myTerms(Principal principal)
    {
        List<TermsResponse> termsResponses =  userService.getTerms(principal.getName());
        return termsResponses;
    }

    @RequestMapping(value = "/users/search", method = RequestMethod.POST)
    public List<UserRepresentation> searchUsers(@RequestBody SearchRequest query)
    {
        return userService.getQueryResponse(query.getQuery());
    }

    @RequestMapping(value = "/rooms/search", method = RequestMethod.POST)
    public List<UserRepresentation> searchRooms(@RequestBody SearchRequest query)
    {
        return roomService.getQueryResponse(query.getQuery());
    }

    @RequestMapping(value = "/users/one", method = RequestMethod.POST)
    public UserDetailsRepresentation userDetails(@RequestBody SearchRequest query)
    {
        return userService.getUserDetails(query.getQuery());
    }

    @RequestMapping(value = "/rooms/one", method = RequestMethod.POST)
    public RoomDetailsRepresentation roomDetails(@RequestBody SearchRequest query, Principal principal)
    {
        return roomService.getRoomDetails(query.getQuery(), principal.getName());
    }
}