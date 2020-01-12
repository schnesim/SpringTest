package de.allianz.test.model;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;

@Entity
@Table(name = "table1")
public class Table1 {

    @Id
    @Column(name = "CRYPTID", unique = true, nullable = false, updatable = false)
    private String cryptId;


    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @LazyCollection(LazyCollectionOption.FALSE)
    @PrimaryKeyJoinColumn
    private Table2 table2;

    public String getCryptId() {
        return cryptId;
    }

    public void setCryptId(String cryptId) {
        this.cryptId = cryptId;
    }

    public Table2 getTable2() {
        return table2;
    }

    public void setTable2(Table2 table2) {
        this.table2 = table2;
    }

}
