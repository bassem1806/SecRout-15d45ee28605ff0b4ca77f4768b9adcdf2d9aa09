package com.example.Securite_Routiere.entities;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
public class Route {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @NotBlank(message = " route est obligatoire ")
    @Column(name = "route_name")
    private String name;

    public Route(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Route() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
