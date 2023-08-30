package de.allianz.test.endpoint;

import de.allianz.test.model.Address;
import de.allianz.test.model.Person;
import de.allianz.test.repo.PersonRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class Endpoints {

    @Autowired
    private PersonRepo personRepo;
    
    @GetMapping("/test")
    public String test() {
        Person p = new Person();
        p.setId(UUID.randomUUID().toString());
        Address a = new Address();
        a.setId(UUID.randomUUID().toString());
        a.setCity("München");
        p.setAddress(a);
        personRepo.save(p);
        return "sakkzess";
    }
    
    @GetMapping("/test2")
    public String test2() {
        Iterable<Person> findAll = personRepo.findAll();
        findAll.forEach(p -> {
            System.out.println(p.getId());
            if (p.getAddress() != null) {
                System.out.println(p.getAddress().getId());
            }
        });
        return "";
    }
    
}
