package za.co.paulscott.person.com.dstvdm.person.repository;

/**
 * Created by pscot on 3/7/2016.
 */

import za.co.paulscott.person.com.dstvdm.person.model.Person;
import org.springframework.data.orient.object.repository.OrientObjectRepository;

/**
 * DAO for {@link Person}.
 */
public interface PersonRepository extends OrientObjectRepository<Person> {

    Person findByEmail(String email);

    Person findByEmailAndActive(String email, boolean active);

}