package com.example.eval_java.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@AllArgsConstructor
@NoArgsConstructor

@Entity
public class Entreprise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column(nullable = false)
    String nom;


//    @OneToMany(mappedBy = "entreprise", cascade = CascadeType.ALL)
//    @JsonManagedReference
//    List<Convention> conventions;

//    @OneToMany(mappedBy = "entreprise", cascade = CascadeType.ALL)
//    List<Utilisateur> utilisateurs;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

//   public List<Convention> getConventions() {
//       return conventions;
//  }
////
//public void setConventions(List<Convention> conventions) {
//   this.conventions = conventions;
//  }

//    public List<Utilisateur> getUtilisateurs() {
//        return utilisateurs;
//    }
//
//    public void setUtilisateurs(List<Utilisateur> utilisateurs) {
//        this.utilisateurs = utilisateurs;
//    }
}

