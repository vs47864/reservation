package de.fraunhofer.iosb.services;

import de.fraunhofer.iosb.entity.Room;
import de.fraunhofer.iosb.entity.User;

import java.util.Date;
import java.util.List;

public interface TermService
{
    void addTerm(User user, List<User> users, Room room, Date from, Date untill);
}
