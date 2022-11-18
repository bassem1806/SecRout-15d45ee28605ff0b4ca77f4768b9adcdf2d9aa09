package com.example.Securite_Routiere.repositories;

import com.example.Securite_Routiere.entities.Temps;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TempsRepository extends JpaRepository<Temps, Long> {
}
