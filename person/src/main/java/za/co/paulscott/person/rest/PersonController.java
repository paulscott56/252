package za.co.paulscott.person.rest;

/**
 * Created by pscot on 3/7/2016.
 */

import za.co.paulscott.person.exceptions.EmailAlreadyInUseException;
import za.co.paulscott.person.exceptions.NoChangesInRecordException;
import za.co.paulscott.person.exceptions.UnknownRecordExcepion;
import za.co.paulscott.person.model.Person;
import za.co.paulscott.person.model.Relationship;
import za.co.paulscott.person.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by pscot on 3/2/2016.
 */
@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonRepository repository;

    @RequestMapping(method = RequestMethod.GET)
    public List<Person> findAllPersons() {
        return repository.findAll();
    }

    @RequestMapping("/findByFirstName")
    public List<Person> findByFirstName(@RequestBody String firstName) {
        return repository.findByFirstName(firstName);
    }

    @RequestMapping("/findByLastName")
    public List<Person> findByLastName(@RequestBody String lastName) {

        return repository.findByLastName(lastName);
    }

    @RequestMapping("/findByEmail")
    public List<Person> findByEmail(@RequestBody String email) {
        return repository.findByEmailAddress(email);
    }

    @RequestMapping(method = RequestMethod.POST)
    public String addPerson(@RequestBody Person person) {
        // check that email is not yet in use, else throw EemailAlreadyInUseException
        String email = person.getEmail();
        if (repository.findByEmailAddress(email).isEmpty()) {
            return repository.addPerson(person);
        } else throw new EmailAlreadyInUseException();
    }

    @RequestMapping(value = "/updateUser", method = RequestMethod.POST)
    public Person updateUser(@RequestBody Person person) {
        String id = person.getId();
        Person oldData = repository.findByOId(id);
        if (oldData == null) {
            throw new UnknownRecordExcepion();
        } else if (oldData.equals(person)) {
            throw new NoChangesInRecordException();
        } else {
            // update the record
            return repository.updatePerson(person);
        }
    }

    @RequestMapping(value = "/createKnows", method = RequestMethod.POST)
    public String createKnows(@RequestBody Relationship rel) {
        Person p1 = repository.findByOId(rel.getId1());
        Person p2 = repository.findByOId(rel.getId2());
        return repository.createKnows(p1, p2);
    }

    @RequestMapping(value = "/breakKnows", method = RequestMethod.POST)
    public String breakKnows(@RequestBody Relationship rel) {
        Person p1 = repository.findByOId(rel.getId1());
        Person p2 = repository.findByOId(rel.getId2());
        return repository.breakKnows(p1, p2);
    }

    @RequestMapping("/listRelationships")
    public List<Person> listRels(@RequestBody String id) {
        Person p = repository.findByOId(id);
        return repository.listRelationships(p);
    }


}