package com.example.eval_java.dao;

import com.example.eval_java.model.Convention;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConventionDao extends JpaRepository<Convention, Integer> {
    // JpaRepository fournit déjà les méthodes CRUD de base
}
