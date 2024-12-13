package com.example.eval_java.service;

import com.example.eval_java.dao.UtilisateurDao;
import com.example.eval_java.model.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UtilisateurService {

    @Autowired
    UtilisateurDao utilisateurDao;

    public List<Utilisateur> getAllUtilisateurs() {
        return utilisateurDao.findAll();
    }

    public List<Utilisateur> getUtilisateursParEntreprise(Integer entrepriseId) {
        return utilisateurDao.findByEntrepriseId(entrepriseId);
    }

    public List<Utilisateur> getAdministrateurs() {
        return utilisateurDao.findByEntrepriseIsNull();
    }

    // Mettre à jour un utilisateur


    // Supprimer un utilisateur

}

