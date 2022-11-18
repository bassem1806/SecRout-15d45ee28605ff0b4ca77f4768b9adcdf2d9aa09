package com.example.Securite_Routiere.repositories;

import com.example.Securite_Routiere.entities.PvAccident;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PvAccidentRepository extends JpaRepository<PvAccident, Long> {


}