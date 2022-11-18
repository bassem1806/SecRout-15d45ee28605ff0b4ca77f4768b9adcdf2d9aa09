package com.example.Securite_Routiere.repositories;


import com.example.Securite_Routiere.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {


    User findByLogin(String login);

}
