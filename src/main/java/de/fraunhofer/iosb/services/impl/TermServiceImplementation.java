package de.fraunhofer.iosb.services.impl;

import de.fraunhofer.iosb.entity.Room;
import de.fraunhofer.iosb.entity.Term;
import de.fraunhofer.iosb.entity.User;
import de.fraunhofer.iosb.entity.key.TermId;
import de.fraunhofer.iosb.repository.TermRepository;
import de.fraunhofer.iosb.repository.UserRepository;
import de.fraunhofer.iosb.representation.TermsResponse;
import de.fraunhofer.iosb.services.TermService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TermServiceImplementation implements TermService
{
    @Autowired
    TermRepository termRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public void addTerm(User user, List<User> users, Room room, Date from, Date until, String title)
    {
        TermId termId = new TermId(from, until, room.roomID);
        Term term = new Term(termId, title, room, user, users);
        termRepository.save(term);
    }

    @Override
    public List<TermsResponse> getFavoriteRoomTerms(String username)
    {
        User user = userRepository.findByUsername(username);
        List<TermsResponse> terms = new ArrayList<>();
        for(Room room : user.getFavorites().values())
        {
            for(Term term : room.getTerms())
            {
                TermsResponse termsResponse = new TermsResponse(term.getTermID().getStartDate(), term.getTermID().getEndDate(), room.getRoomID(), term.getTitle());
                terms.add(termsResponse);
            }
        }
        return terms;
    }
}
