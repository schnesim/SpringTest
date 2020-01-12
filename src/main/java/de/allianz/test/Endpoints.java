package de.allianz.test;

import de.allianz.test.model.Table2;
import de.allianz.test.model.Table1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class Endpoints {

    @Autowired
    private Table1Repository repo;

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
}
