package com.example.Securite_Routiere.repositories;


import com.example.Securite_Routiere.entities.TypeRoute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeRouteRepository extends JpaRepository<TypeRoute, Long> {
}
