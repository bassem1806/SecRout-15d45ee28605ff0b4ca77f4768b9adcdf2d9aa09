package com.example.Securite_Routiere.repositories;


import com.example.Securite_Routiere.entities.CauseAccident;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CauseAccidentRepository extends JpaRepository<CauseAccident, Long> {

}
