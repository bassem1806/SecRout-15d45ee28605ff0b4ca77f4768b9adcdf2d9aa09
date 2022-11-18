package com.example.Securite_Routiere.entities;


import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
public class Delegation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "delegation_id")
    private long delegationId;

    @NotBlank(message = "Delegation nom est obligatoire")
    @Column(name = "delegation_name")
    private String name;


    /**** Many To One  gov****/
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "gouvernorat_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Gouvernorat gouvernorat;

    public Delegation(long delegationId, String name) {
        this.delegationId = delegationId;
        this.name = name;
    }

    public Delegation() {
    }

    public Gouvernorat getGouvernorat() {
        return gouvernorat;
    }

    public void setGouvernorat(Gouvernorat gouvernorat) {
        this.gouvernorat = gouvernorat;
    }

    public long getDelegationId() {
        return delegationId;
    }

    public void setDelegationId(long delegationId) {
        this.delegationId = delegationId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
