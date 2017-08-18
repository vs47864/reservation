package de.fraunhofer.iosb.services.impl;

import de.fraunhofer.iosb.entity.Room;
import de.fraunhofer.iosb.entity.Term;
import de.fraunhofer.iosb.entity.User;
import de.fraunhofer.iosb.entity.key.TermId;
import de.fraunhofer.iosb.repository.TermRepository;
import de.fraunhofer.iosb.services.TermService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TermServiceImplementation implements TermService
{
    @Autowired
    TermRepository termRepository;

    @Override
    public void addTerm(User user, List<User> users, Room room, Date from, Date untill)
    {
        TermId termId = new TermId(from, untill, room.roomID);
        Term term = new Term(termId, room, user, users);
        termRepository.save(term);
    }
}
