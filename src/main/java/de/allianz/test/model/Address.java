package de.allianz.test.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

//@Entity
public class Address {


//    @OneToOne
//    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private String person;

    private String street;
}
