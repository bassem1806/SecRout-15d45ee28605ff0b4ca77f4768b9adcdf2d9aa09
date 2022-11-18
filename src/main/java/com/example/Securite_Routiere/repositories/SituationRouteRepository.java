package com.example.Securite_Routiere.repositories;


import com.example.Securite_Routiere.entities.SituationRoute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SituationRouteRepository extends JpaRepository<SituationRoute, Long> {
}
