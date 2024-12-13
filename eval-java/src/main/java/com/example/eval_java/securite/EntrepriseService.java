package com.example.eval_java.securite;

import com.example.eval_java.dao.EntrepriseDao;
import com.example.eval_java.model.Entreprise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EntrepriseService {

    @Autowired
    EntrepriseDao entrepriseDao;

    // Récupérer toutes les entreprises
    public List<Entreprise> getAllEntreprises() {
        return entrepriseDao.findAll();
    }

    // Récupérer une entreprise par ID
    public Entreprise getEntrepriseById(Integer id) {
        return entrepriseDao.findById(id)
                .orElseThrow(() -> new RuntimeException("Entreprise non trouvée avec l'id : " + id));
    }

}
