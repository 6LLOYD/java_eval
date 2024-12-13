package com.example.eval_java.controller;

import com.example.eval_java.dao.EntrepriseDao;
import com.example.eval_java.dao.UtilisateurDao;
import com.example.eval_java.model.Entreprise;
import com.example.eval_java.model.Utilisateur;
import com.example.eval_java.securite.IsAdmin;
import com.example.eval_java.service.UtilisateurService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/utilisateurs")
public class UtilisateurController {

    @Autowired
    UtilisateurService utilisateurService;

    @Autowired
    UtilisateurDao utilisateurDao;

    @Autowired
    EntrepriseDao entrepriseDao;

    @Autowired
    BCryptPasswordEncoder encoder;

    @GetMapping
    public List<Utilisateur> getAllUtilisateurs() {
        return utilisateurService.getAllUtilisateurs();
    }


    @GetMapping("/entreprise/{entrepriseId}")
    public List<Utilisateur> getUtilisateursParEntreprise(@PathVariable Integer entrepriseId) {
        return utilisateurService.getUtilisateursParEntreprise(entrepriseId);
    }


    @GetMapping("/administrateurs")
    public List<Utilisateur> getAdministrateurs() {
        return utilisateurService.getAdministrateurs();
    }

    @PostMapping("/add")
    public ResponseEntity<Utilisateur> createUtilisateur(
            @RequestBody @Valid Utilisateur utilisateur) {

        utilisateur.setId(null);

        // Encodage du mot de passe
        utilisateur.setPassword(encoder.encode(utilisateur.getPassword()));

        // Vérification de l'entreprise si elle est liée
        if (utilisateur.getEntreprise() != null && utilisateur.getEntreprise().getId() != null) {
            Entreprise entreprise = entrepriseDao.findById(utilisateur.getEntreprise().getId())
                    .orElseThrow(() -> new RuntimeException("Entreprise non trouvée"));
            utilisateur.setEntreprise(entreprise);
        } else {
            // Si l'utilisateur n'est pas lié à une entreprise, c'est un administrateur
            utilisateur.setEntreprise(null);
        }

        utilisateurDao.save(utilisateur);

        return new ResponseEntity<>(utilisateur, HttpStatus.CREATED);
    }


    @PutMapping("update/{id}")
    public ResponseEntity<Utilisateur> updateUtilisateur(@PathVariable Integer id, @RequestBody Utilisateur utilisateurEnvoye) {
        Utilisateur utilisateur = utilisateurDao.findById(id)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));

        utilisateur.setEmail(utilisateurEnvoye.getEmail());

        utilisateur.setEntreprise(utilisateurEnvoye.getEntreprise());

        //Si l'utilisateur a un nouveau mot de passe, on le hash le nouveau
        if(utilisateur.getPassword() != null) {
            utilisateur.setPassword(encoder.encode(utilisateur.getPassword()));
        }
        utilisateurDao.save(utilisateur);
        return new ResponseEntity<>(utilisateur, HttpStatus.OK);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Utilisateur> deleteUtilisateur(@PathVariable Integer id) {
        Utilisateur utilisateur = utilisateurDao.findById(id)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));

        utilisateurDao.delete(utilisateur);
        return new ResponseEntity<>(utilisateur, HttpStatus.OK);
    }
}

