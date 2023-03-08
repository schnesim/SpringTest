package de.allianz.test.repo;

import de.allianz.test.model.Address;
import org.springframework.data.repository.CrudRepository;

public interface AddressRepo extends CrudRepository<Address, String> {
    
}
