package de.fraunhofer.iosb.repository;


import de.fraunhofer.iosb.entity.Room;
import de.fraunhofer.iosb.entity.Term;
import de.fraunhofer.iosb.entity.key.TermId;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface TermRepository extends CrudRepository<Term, TermId>
{
    List<Term> findByRoomAndTermID_StartDateGreaterThanOrderByTermID(Room room, Date date);

    Term  findByRoomAndTermID_StartDateLessThanEqualAndTermID_EndDateGreaterThanEqual(Room room, Date date, Date date1);
}
