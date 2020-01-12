package de.allianz.test.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "table2")
public class Table2 implements Serializable {
	
	@Id
	@Column(name = "FK_CRYPTID", unique = false, nullable = false, updatable = false)
	private String fkCryptId;
	
	private String persId;

    public String getFkCryptId() {
        return fkCryptId;
    }

    public void setFkCryptId(String fkCryptId) {
        this.fkCryptId = fkCryptId;
    }

    public String getPersId() {
        return persId;
    }

    public void setPersId(String persId) {
        this.persId = persId;
    }

}
