package com.example.Securite_Routiere.repositories;

import com.example.Securite_Routiere.entities.Gouvernorat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GouvernoratRepository extends JpaRepository<Gouvernorat, Long> {

}


