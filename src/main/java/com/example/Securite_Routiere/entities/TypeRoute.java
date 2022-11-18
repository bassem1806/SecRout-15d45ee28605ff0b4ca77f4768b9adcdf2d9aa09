package com.example.Securite_Routiere.entities;

import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity

@ToString

public class TypeRoute {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long typerouteId;

    @NotBlank(message = "Type route est obligatoire ")
    @Column(name = "typeroute_name")
    private String name;

    public TypeRoute(long typerouteId, String name) {
        this.typerouteId = typerouteId;
        this.name = name;
    }

    public TypeRoute() {
    }

    public long getId() {
        return typerouteId;
    }

    public void setId(long typerouteId) {
        this.typerouteId = typerouteId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
