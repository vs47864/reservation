package de.fraunhofer.iosb.services.impl;

import de.fraunhofer.iosb.entity.Room;
import de.fraunhofer.iosb.entity.Term;
import de.fraunhofer.iosb.entity.User;
import de.fraunhofer.iosb.entity.key.TermId;
import de.fraunhofer.iosb.repository.TermRepository;
import de.fraunhofer.iosb.repository.UserRepository;
import de.fraunhofer.iosb.representation.TermDetailsResponse;
import de.fraunhofer.iosb.representation.TermsResponse;
import de.fraunhofer.iosb.representation.UserRepresentation;
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
                TermsResponse termsResponse = new TermsResponse(term.getTermID().getStartDate(), term.getTermID().getEndDate(), room.getName(), term.getTitle(), room.getRoomID());
                terms.add(termsResponse);
            }
        }
        return terms;
    }

    @Override
    public TermDetailsResponse getTerm(TermsResponse term) {
        TermId termId = new TermId(term.getStartDate(), term.getEndDate(), term.getRoomId());
        Term term1 = termRepository.findOne(termId);
        List<UserRepresentation> userRepresentations = new ArrayList<>();
        for (User user : term1.getUsers())
        {
            UserRepresentation userRepresentation = new UserRepresentation(user.getName() + user.lastname, user.username);
            userRepresentations.add(userRepresentation);
        }
        UserRepresentation initUser = new UserRepresentation(term1.getUser().getName() + term1.getUser().lastname, term1.getUser().getUsername());
        TermDetailsResponse termDetailsResponse =new TermDetailsResponse(term, initUser, userRepresentations);
        return termDetailsResponse;
    }
}
