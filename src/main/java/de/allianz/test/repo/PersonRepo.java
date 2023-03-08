package de.allianz.test.repo;

import de.allianz.test.model.Person;
import org.springframework.data.repository.CrudRepository;

public interface PersonRepo extends CrudRepository<Person, String> {

    
}
