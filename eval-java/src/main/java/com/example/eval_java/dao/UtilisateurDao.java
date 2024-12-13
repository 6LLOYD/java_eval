package com.example.eval_java.dao;

import com.example.eval_java.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UtilisateurDao extends JpaRepository<Utilisateur, Integer> {


    // Trouver tous les utilisateurs par entreprise
    List<Utilisateur> findByEntrepriseId(Integer entrepriseId);


    // Trouver tous les administrateurs
    List<Utilisateur> findByEntrepriseIsNull();
}
