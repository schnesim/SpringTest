package de.allianz.test.endpoint;

import de.allianz.test.repo.Table1Repository;
import de.allianz.test.model.JasonModel;
import de.allianz.test.model.Table2;
import de.allianz.test.model.Table1;
import de.allianz.test.service.ServiceTwo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class Endpoints {

    @Autowired
    private Table1Repository repo;
    
    @Autowired
    private ServiceTwo serviceTwo;
    
    @GetMapping("/test")
    public String test() {
        return serviceTwo.callServiceOne();
    }

    @RequestMapping("/save")
    public String doSomething() {
        Table1 t1 = new Table1();
        t1.setCryptId(UUID.randomUUID().toString());
        Table2 t2 = new Table2();
        t2.setFkCryptId(t1.getCryptId());
        t1.setTable2(t2);
        repo.save(t1);

        return "done";
    }

    @PostMapping(name = "/post0r", consumes = {"application/json"})
    public String somePostEndpoint(@RequestBody JasonModel model) {
        System.out.println(model.getName());
        return "I did it";
    }
}
