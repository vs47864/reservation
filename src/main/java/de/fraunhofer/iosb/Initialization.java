package de.fraunhofer.iosb;

import de.fraunhofer.iosb.entity.Room;
import de.fraunhofer.iosb.entity.User;
import de.fraunhofer.iosb.repository.RoomRepository;
import de.fraunhofer.iosb.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
public class Initialization  implements CommandLineRunner
{
    private static final Logger LOG = LoggerFactory.getLogger(Initialization.class);

    private RoomRepository repoRoom;
    private UserRepository userRepo;

    @Autowired
    public Initialization(RoomRepository repoRoom, UserRepository userRepo)
    {
        this.repoRoom = repoRoom;
        this.userRepo = userRepo;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        Room r18 = new Room("BEACON 39:91:DB", "HIWI 1", "token");
        repoRoom.save(r18);

        Room r19 = new Room("BEACON 39:8C:22", "HIWI 2", "token");
        repoRoom.save(r19);

        Room r22 = new Room("C8-22", "http://localhost:8080/rooms/2", "neki token");
        repoRoom.save(r22);

        Room all = new Room("all", "http://localhost:8080/rooms/all", "neki token");
        repoRoom.save(all);

        User userAdmin = new User("admin", "admin", "Admin", "Admin", "admin@fer.hr", "0");
        userRepo.save(userAdmin);

        User userAdmin1 = new User("admin@fer.hr", "admin1", "Admin", "Admin", "admin@fer.hr", "0");
        userAdmin1.getFavorites().put(r22.getRoomID(), r22);
        userAdmin1.setToken("e67a3e52-24d8-44cc-bec7-5bd2371c55d9");
        userRepo.save(userAdmin1);

        User user18 = new User("user18", "user18", "User18", "User18", "user18@fer.hr", "1");
        user18.setToken("1fa5fbc4-83b7-49cf-b40b-5cc61a3cf657");
        user18.setCurentRoom(r18);
        userRepo.save(user18);

        User user22 = new User("user22", "user22", "User22", "User22", "user22@fer.hr", "2");
        user22.setCurentRoom(r18);
        userRepo.save(user22);


        addUserForRoom(r18, "bb46865", "bilokoji", "Boris", "Belaj", "boris.belaj@fer.hr", "3");
        addUserForRoom(r18, "luka", "svejedno", "Luka", "Ušalj", "luka.usalj@fer.hr", "4");
        addUserForRoom(r18, "vatroslav", "neznam", "Vatroslav", "Bajt", "vatroslav.bajt@fer.hr", "5");
        addUserForRoom(r18, "mario", "kusek", "Mario", "Kušek", "mario.kusek@fer.hr", "6");
        addUserForRoom(r18, "marko", "pavelic", "Marko", "Pavelić", "marko.pavelic@fer.hr", "7");

    }

    private void addUserForRoom(Room room, String username, String password, String firstName, String lastName, String email, String id) {
        LOG.info("Initialized user '{}' pass '{}' with open roll for room {}", username, password, room.getRoomID());
        User userBB = new User(username, password, firstName, lastName, email, id);
        userRepo.save(userBB);
    }

}
