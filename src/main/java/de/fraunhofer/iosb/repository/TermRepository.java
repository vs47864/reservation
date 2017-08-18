package de.fraunhofer.iosb.repository;


import de.fraunhofer.iosb.entity.Room;
import de.fraunhofer.iosb.entity.Term;
import de.fraunhofer.iosb.entity.key.TermId;
import org.springframework.data.repository.CrudRepository;

public interface TermRepository extends CrudRepository<Term, TermId>
{
}
