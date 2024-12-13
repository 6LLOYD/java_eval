package com.example.eval_java.model;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Convention {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    String nom;

    @NotNull(message = "Le champ subvention ne peut pas être nul")
    @PositiveOrZero(message = "La subvention ne peut pas être négative")
    float subvention;


    @NotNull(message = "Le champ salarieMaximum ne peut pas être nul")
    @Min(value = 1, message = "Le nombre maximum de salariés doit être supérieur ou égal à 1")
    int salarieMaximum;

    @ManyToOne
    @JoinColumn(name = "entreprise_id", nullable = false)
    @JsonBackReference
    Entreprise entreprise;

//    @OneToMany(mappedBy = "convention", cascade = CascadeType.ALL)
//    List<Salarie> salaries;

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

    public float getSubvention() {
        return subvention;
    }

    public void setSubvention(float subvention) {
        this.subvention = subvention;
    }

    public int getSalarieMaximum() {
        return salarieMaximum;
    }

    public void setSalarieMaximum(int salarieMaximum) {
        this.salarieMaximum = salarieMaximum;
    }

    public Entreprise getEntreprise() {
        return entreprise;
    }

    public void setEntreprise(Entreprise entreprise) {
        this.entreprise = entreprise;
    }

//    public List<Salarie> getSalaries() {
//        return salaries;
//    }
//
//    public void setSalaries(List<Salarie> salaries) {
//        this.salaries = salaries;
//    }
}


