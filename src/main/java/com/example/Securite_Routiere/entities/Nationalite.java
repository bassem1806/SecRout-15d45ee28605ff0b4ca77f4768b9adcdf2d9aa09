package com.example.Securite_Routiere.entities;

import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Entity

@ToString
public class Nationalite {
    @Id

    private long nationaliteId;


    @NotBlank(message = "Nationalité est obligatoire")
    @Column(name = "nationalite_name")
    private String name;

    public Nationalite(long nationaliteId, String libelle) {
        this.nationaliteId = nationaliteId;
        this.name = libelle;
    }

    public Nationalite() {
    }

    public long getId() {
        return nationaliteId;
    }

    public void setId(long id) {
        this.nationaliteId = id;
    }

    public String getLibelle() {
        return name;
    }

    public void setLibelle(String libelle) {
        this.name = libelle;
    }
}
