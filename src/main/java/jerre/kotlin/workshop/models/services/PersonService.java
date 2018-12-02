package jerre.kotlin.workshop.models.services;

import jerre.kotlin.workshop.models.Person;
import jerre.kotlin.workshop.models.Utils;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class PersonService {

    Set<Person> persons;

    public PersonService() {
        persons = new HashSet<>();
    }

    public boolean addPerson(Person person) {
        if (person.getId() == null) {
            person.setId(Utils.getNextIdInSequence(persons));
        }

        return persons.add(person);
    }

}
