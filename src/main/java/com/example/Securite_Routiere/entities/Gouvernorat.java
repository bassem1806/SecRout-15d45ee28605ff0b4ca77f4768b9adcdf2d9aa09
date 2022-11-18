package com.example.Securite_Routiere.entities;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity

public class Gouvernorat {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    @Column(name = "gouvernorat_id")
    public long gouvernoratId;
    @NotBlank(message = "Cause Accident est obligatoire ")
    @Column(name = "gouvernorat_name")
    private String name;


    /**** one To many delegation****/
    @OneToMany(mappedBy = "gouvernorat")
    private List<Delegation> delegations;


    public Gouvernorat(long gouvernoratId, String name) {
        this.gouvernoratId = gouvernoratId;
        this.name = name;
    }

    public Gouvernorat() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return gouvernoratId;
    }

    public void setId(long id) {
        this.gouvernoratId = id;
    }

    public long getGouvernoratId() {
        return gouvernoratId;
    }

    public void setGouvernoratId(long gouvernoratId) {
        this.gouvernoratId = gouvernoratId;
    }

    public List<Delegation> getDelegations() {
        return delegations;
    }

    public void setDelegations(List<Delegation> delegations) {
        this.delegations = delegations;
    }


}
