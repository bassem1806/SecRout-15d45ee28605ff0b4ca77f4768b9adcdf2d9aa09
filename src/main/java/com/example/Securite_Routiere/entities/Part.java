package com.example.Securite_Routiere.entities;


import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Entity

@ToString
public class Part {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private long partId;

    @NotBlank(message = "Participant obligatoir ")
    @Column(name = "part_name")
    private String name;


    /**** Many To many part   ****/
    @ManyToMany(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    @JoinTable(name = "pvaccident_part",
            joinColumns = @JoinColumn(name = "part_id"),
            inverseJoinColumns = @JoinColumn(name = "pvaccid_id"))
    private Set<PvAccident1> pvAccident1s;

    public Part(long partId, String name) {
        this.partId = partId;
        this.name = name;
    }

    public Part() {
    }

    public Set<PvAccident1> getPvAccident1s() {
        return pvAccident1s;
    }

    public void setPvAccident1s(Set<PvAccident1> pvAccident1s) {
        this.pvAccident1s = pvAccident1s;
    }

    public long getId() {
        return partId;
    }

    public void setId(long id) {
        this.partId = partId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
