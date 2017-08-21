package de.fraunhofer.iosb.services.impl;

import de.fraunhofer.iosb.entity.Room;
import de.fraunhofer.iosb.entity.User;
import de.fraunhofer.iosb.repository.RoomRepository;
import de.fraunhofer.iosb.repository.UserRepository;
import de.fraunhofer.iosb.representation.RoomRepresentation;
import de.fraunhofer.iosb.representation.UserRepresentation;
import de.fraunhofer.iosb.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImplementation implements UserService
{
    @Autowired
    private UserRepository repo;

    @Autowired
    private RoomRepository roomRepository;

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

    @Override
    public List<User> getUsersByIds(List<String> ids)
    {
        List<User> users = new ArrayList<>();
        for (String id : ids)
        {
            User tmp = repo.findByUsername(id);
            if(tmp != null)
            {
                users.add(repo.findByUsername(id));
            }
        }
        return users;
    }

    @Override
    public User findUser(String name) {
        return repo.findByUsername(name);
    }

    @Override
    public void scheduleCurrentRoom(List<User> users, Room room, Date startTime, Date endTime)
    {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                setCurrentRoom(room, users);
            }
        }, startTime);

        timer.schedule(new TimerTask() {
            public void run() {
                unSetCurrentRoom(users);
            }
        }, endTime);
    }

    public void setCurrentRoom(Room room, List<User> users)
    {
        for(User user : users)
        {
            user.setCurentRoom(room);
        }
    }

    public void unSetCurrentRoom(List<User> users)
    {
        for(User user : users)
        {
            user.setCurentRoom(null);
        }
    }

    @Override
    public void makeFavorite(String roomId, User user)
    {
        Room room = roomRepository.findByRoomID(roomId);
        if (user.getFavorites().containsKey(roomId))
        {
            user.getFavorites().remove(roomId);
            repo.save(user);
            return;
        }
        user.getFavorites().put(roomId, room);
        repo.save(user);
    }

    @Override
    public List<RoomRepresentation> getFavoriteRoom(String username)
    {
        List<RoomRepresentation> result = new ArrayList<>();
        User user = repo.findByUsername(username);
        for (Room room : user.getFavorites().values())
        {
            RoomRepresentation representation = new RoomRepresentation(room.roomID, room.name, room.occupied, new Date(), new Date(), true);
            result.add(representation);
        }
        return result;
    }
}
