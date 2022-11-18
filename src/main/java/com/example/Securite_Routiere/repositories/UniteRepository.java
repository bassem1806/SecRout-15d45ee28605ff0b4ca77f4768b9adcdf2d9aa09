package com.example.Securite_Routiere.repositories;

import com.example.Securite_Routiere.entities.Unite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UniteRepository extends JpaRepository<Unite, Long> {
}
