package com.example.Securite_Routiere.entities;

import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity

@ToString
public class SignauxCirculation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private long signeId;

    @NotBlank(message = "Cause Accident est obligatoire ")
    @Column(name = "Signe")
    private String name;

    public SignauxCirculation(long signeId, String name) {
        this.signeId = signeId;
        this.name = name;
    }

    public SignauxCirculation() {
    }

    public long getSigneId() {
        return signeId;
    }

    public void setSigneId(long signeId) {
        this.signeId = signeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
