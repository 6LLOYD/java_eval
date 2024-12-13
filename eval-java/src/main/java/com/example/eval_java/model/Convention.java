package com.example.eval_java.model;
import jakarta.persistence.*;
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

    float subvention;

    int salarieMaximum;

    @ManyToOne
    @JoinColumn(name = "entreprise_id", nullable = false)
    Entreprise entreprise;

    @OneToMany(mappedBy = "convention", cascade = CascadeType.ALL)
    List<Salarie> salaries;

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

    public List<Salarie> getSalaries() {
        return salaries;
    }

    public void setSalaries(List<Salarie> salaries) {
        this.salaries = salaries;
    }
}


