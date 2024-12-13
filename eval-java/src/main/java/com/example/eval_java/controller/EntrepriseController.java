package com.example.eval_java.controller;

import com.example.eval_java.dao.EntrepriseDao;
import com.example.eval_java.model.Entreprise;
import com.example.eval_java.model.Utilisateur;
import com.example.eval_java.securite.EntrepriseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/entreprises")
public class EntrepriseController {

    @Autowired
    EntrepriseService entrepriseService;

    @Autowired
    EntrepriseDao entrepriseDao;

    @GetMapping
    public List<Entreprise> getAllEntreprises() {
        return entrepriseService.getAllEntreprises();

    }

    @GetMapping("/{id}")
    public Entreprise getEntrepriseById(@PathVariable Integer id) {
        return entrepriseService.getEntrepriseById(id);

    }

    @PostMapping("/add")
    public ResponseEntity<Entreprise> createEntreprise(@RequestBody @Valid Entreprise entreprise) {

        entreprise.setId(null);

        entrepriseDao.save(entreprise);

        return new ResponseEntity<>(entreprise, HttpStatus.CREATED);
    }


    // Endpoint : Mettre à jour une entreprise
    @PutMapping("/update/{id}")
    public ResponseEntity<Entreprise> updateEntreprise(
             @RequestBody @Valid Entreprise entrepriseDetails, @PathVariable Integer id) {

        entrepriseDetails.setId(id);

        Optional<Entreprise> optionalEntreprise = entrepriseDao.findById(id);

        //si l'utilisateur n'existe pas
        if(optionalEntreprise.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        entrepriseDao.save(entrepriseDetails);

        return new ResponseEntity<>(optionalEntreprise.get(), HttpStatus.OK);
    }

    // Endpoint : Supprimer une entreprise
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Entreprise> deleteEntreprise(@PathVariable Integer id) {

        Optional<Entreprise> optionalEntreprise = entrepriseDao.findById(id);
        //si l'utilisateur n'existe pas
        if(optionalEntreprise.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        entrepriseDao.deleteById(id);

        return new ResponseEntity<>(optionalEntreprise.get(), HttpStatus.OK);
    }
}
