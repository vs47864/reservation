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

/**
 * This class is listening for requests from the user application.
 *
 * @author Viseslav Sako
 */
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

    /**
     * This method responsible for listening for login requests. It checks if the username and passwords are
     * OK and which role the users has. It returns access token.
     *
     * @param userCredential username and password
     * @return token and role
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public TokenRepresentation login(@Validated @RequestBody UserCredentialRepresentation userCredential)
    {
        LOG.debug("Received at /login {}", userCredential);
        boolean checked = loginService.checkUserNameAndPassword(
                userCredential.getUsername(),
                userCredential.getPassword());

        User user = userService.findUser(userCredential.getUsername());

        if(checked)
        {
            TokenRepresentation tokenRepresentation = new TokenRepresentation(loginService.createToken(user),loginService.checkIfAdmin(user));
            LOG.debug("Returns at /login {}", tokenRepresentation);
            return tokenRepresentation;
        } else
            {
            LOG.debug("Returns at /login UnauthorizedException");
            throw new UnauthorizedException();
        }
    }

    @RequestMapping(value = "/nearby", method = RequestMethod.POST)
    public List<RoomRepresentation> nearbyRooms(@RequestBody NearbyRequest nearbyRequest, Principal principal)
    {
        User user = userService.findUser(principal.getName());
        return roomService.getListOfRooms(nearbyRequest, user);
    }

    /**
     * This method is responsible for listening for request for favorite rooms.
     *
     *
     * @param principal user who has sent the request
     * @return list of favorite rooms
     */
    @RequestMapping(value = "/favorites", method = RequestMethod.GET)
    public List<RoomRepresentation> favoriteRooms(Principal principal)
    {
        return userService.getFavoritesRoom(principal.getName());
    }

    /**
     * This method returns all dates for favorite rooms.
     *
     * @param principal user who has sent the request
     * @return list od terms of favorite rooms
     */
    @RequestMapping(value = "/favorites/terms", method = RequestMethod.GET)
    public List<TermsResponse> favoriteRoomsTerms(Principal principal)
    {
        return termService.getFavoriteRoomsTerms(principal.getName());
    }

    /**
     * In this method room is se reserved for desired time period but first is checked if the room is available;
     *
     * @param id of room that user wants to reserve
     * @param principal user who has sent the request
     * @param reservationRequest oder info about the date
     * @return if reservation was success or not
     */
    @RequestMapping(value = "/room/{id}", method = RequestMethod.POST)
    public ReservationResponse reservationRequest(@PathVariable(value="id") String id, Principal principal, @RequestBody ReservationRequest reservationRequest)
    {
        ReservationResponse reservationResponse = new ReservationResponse();
        Pair<Date, Date> dates = roomService.parseDates(reservationRequest.getStartTime(), reservationRequest.getEndTime(), reservationRequest.getDate());
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

            if(users.contains(user))
            {
                users.remove(user);
            }
            Room room = roomService.findRoom(id);
            termService.addTerm(user, users, room, dates.getKey(), dates.getValue(), reservationRequest.getTitle());
            reservationResponse.setSuccess(true);
            users.add(user);
            userService.scheduleCurrentRoom(users, room, dates.getKey(), dates.getValue());
        }
        return reservationResponse;
    }

    /**
     * In this method room is se reserved for desired time period but first is checked if the room is available;
     *
     * @param id of room that user wants to reserve
     * @param reserveRequest oder info about the date
     * @return if reservation was success or not
     */
    @RequestMapping(value = "/room/quick/{id}", method = RequestMethod.POST)
    public ReservationResponse reservationRequest(@PathVariable(value="id") String id, @RequestBody QuickRoomReservationRequest reserveRequest)
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

    /**
     * This method returns list of all users
     *
     * @return list of all users
     */
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public List<UserRepresentation> users()
    {
        return userService.getAllUsersInRepresentation();
    }

    /**
     * This method is used for adding or removing room from favorites
     *
     * @param id room to add or remove from favorites
     * @param principal ser who wants do add or remove favorite room
     * @return if it was success or not
     */
    @RequestMapping(value = "/room/favorite/{id}", method = RequestMethod.GET)
    public ReservationResponse makeFavorite(@PathVariable(value="id") String id, Principal principal)
    {
        User user = userService.findUser(principal.getName());
        ReservationResponse reservationResponse = new ReservationResponse();
        userService.makeFavorite(id, user);
        return reservationResponse;
    }

    /**
     * Method for returning term details
     *
     * @param term basic information about the term
     * @return term details
     */
    @RequestMapping(value = "/favorites/terms/", method = RequestMethod.POST)
    public TermDetailsResponse termDetailsResponse(@RequestBody TermsResponse term)
    {
        return termService.getTerm(term);
    }

    /**
     * This is a method which returns all term of current user
     *
     * @param principal user
     * @return all terms
     */
    @RequestMapping(value = "/users/terms", method = RequestMethod.GET)
    public List<TermsResponse> myTerms(Principal principal)
    {
        return userService.getTerms(principal.getName());
    }

    /**
     * This method return result for searching for user.
     *
     * @param query string which is used to compare
     * @return list of user that contains query in their name or surname
     */
    @RequestMapping(value = "/users/search", method = RequestMethod.POST)
    public List<UserRepresentation> searchUsers(@RequestBody SearchRequest query)
    {
        return userService.getQueryResponse(query.getQuery());
    }
    /**
     * This method return result for searching for room.
     *
     * @param query string which is used to compare
     * @return list of rooms that contains query in their name
     */
    @RequestMapping(value = "/rooms/search", method = RequestMethod.POST)
    public List<UserRepresentation> searchRooms(@RequestBody SearchRequest query)
    {
        return roomService.getQueryResponse(query.getQuery());
    }

    /**
     * This is a method for getting details for requested user.
     *
     * @param query id of a user that we are looking for.
     * @return details of requested user
     */
    @RequestMapping(value = "/users/one", method = RequestMethod.POST)
    public UserDetailsRepresentation userDetails(@RequestBody SearchRequest query)
    {
        return userService.getUserDetails(query.getQuery());
    }

    /**
     * This is a method for getting details for requested room.
     *
     * @param query id of a user that we are looking for.
     * @return details of requested room
     */
    @RequestMapping(value = "/rooms/one", method = RequestMethod.POST)
    public RoomDetailsRepresentation roomDetails(@RequestBody SearchRequest query, Principal principal)
    {
        return roomService.getRoomDetails(query.getQuery(), principal.getName());
    }
}