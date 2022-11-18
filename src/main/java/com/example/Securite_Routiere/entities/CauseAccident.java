package com.example.Securite_Routiere.entities;

import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Entity
@ToString
public class CauseAccident {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long causeaccidentId;


    @NotBlank(message = "Cause Accident est obligatoire ")
    @Column(name = "casuseaccident_name")
    private String name;


    /**** Many To many pv accident1  ****/

    @ManyToMany(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    @JoinTable(name = "pvaccident_causeaccident",
            joinColumns = @JoinColumn(name = "causeaccident_id"),
            inverseJoinColumns = @JoinColumn(name = "pvaccid_id"))
    private Set<PvAccident1> pvAccident1s;

    public CauseAccident(long causeaccidentId, String name) {
        this.causeaccidentId = causeaccidentId;
        this.name = name;
    }

    public CauseAccident() {
    }

    public Set<PvAccident1> getPvAccident1s() {
        return pvAccident1s;
    }

    public void setPvAccident1s(Set<PvAccident1> pvAccident1s) {
        this.pvAccident1s = pvAccident1s;
    }

    public long getCauseaccidentId() {
        return causeaccidentId;
    }

    public void setCauseaccidentId(long causeaccidentId) {
        this.causeaccidentId = causeaccidentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
