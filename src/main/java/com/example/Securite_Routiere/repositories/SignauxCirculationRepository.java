package com.example.Securite_Routiere.repositories;

import com.example.Securite_Routiere.entities.SignauxCirculation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SignauxCirculationRepository extends JpaRepository<SignauxCirculation, Long> {
}
