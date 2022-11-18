package com.example.Securite_Routiere.entities;


import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity

@ToString

public class Temps {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private long tempsId;

    @NotBlank(message = "Temps lors de l'accident ")
    @Column(name = "temps_name")
    private String name;


    public Temps(long tempsId, String name) {
        this.tempsId = tempsId;
        this.name = name;
    }

    public Temps() {
    }

    public long getId() {
        return tempsId;
    }

    public void setId(long id) {
        this.tempsId = id;
    }

    public String getName() {
        return name;
    }
}
