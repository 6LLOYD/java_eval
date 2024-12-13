package com.example.eval_java.controller;

import com.example.eval_java.model.Entreprise;
import com.example.eval_java.securite.EntrepriseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/entreprises")
public class EntrepriseController {

    @Autowired
    EntrepriseService entrepriseService;

    // Endpoint : Récupérer toutes les entreprises
    @GetMapping
    public ResponseEntity<List<Entreprise>> getAllEntreprises() {
        List<Entreprise> entreprises = entrepriseService.getAllEntreprises();
        return new ResponseEntity<>(entreprises, HttpStatus.OK);
    }

    // Endpoint : Récupérer une entreprise par ID
    @GetMapping("/{id}")
    public ResponseEntity<Entreprise> getEntrepriseById(@PathVariable Integer id) {
        Entreprise entreprise = entrepriseService.getEntrepriseById(id);
        return new ResponseEntity<>(entreprise, HttpStatus.OK);
    }

    // Endpoint : Créer une entreprise
    @PostMapping
    public ResponseEntity<Entreprise> createEntreprise(@RequestBody Entreprise entreprise) {
        Entreprise createdEntreprise = entrepriseService.createEntreprise(entreprise);
        return new ResponseEntity<>(createdEntreprise, HttpStatus.CREATED);
    }

    // Endpoint : Mettre à jour une entreprise
    @PutMapping("/{id}")
    public ResponseEntity<Entreprise> updateEntreprise(
            @PathVariable Integer id, @RequestBody Entreprise entrepriseDetails) {
        Entreprise updatedEntreprise = entrepriseService.updateEntreprise(id, entrepriseDetails);
        return new ResponseEntity<>(updatedEntreprise, HttpStatus.OK);
    }

    // Endpoint : Supprimer une entreprise
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEntreprise(@PathVariable Integer id) {
        entrepriseService.deleteEntreprise(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
