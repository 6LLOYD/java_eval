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

    // Créer une entreprise
    public Entreprise createEntreprise(Entreprise entreprise) {
        return entrepriseDao.save(entreprise);
    }

    // Mettre à jour une entreprise
    public Entreprise updateEntreprise(Integer id, Entreprise entrepriseDetails) {
        Entreprise entreprise = entrepriseDao.findById(id)
                .orElseThrow(() -> new RuntimeException("Entreprise non trouvée avec l'id : " + id));

        entreprise.setNom(entreprise.getNom());
        return entrepriseDao.save(entreprise);
    }

    // Supprimer une entreprise
    public void deleteEntreprise(Integer id) {
        Entreprise entreprise = entrepriseDao.findById(id)
                .orElseThrow(() -> new RuntimeException("Entreprise non trouvée avec l'id : " + id));

        entrepriseDao.delete(entreprise);
    }
}
