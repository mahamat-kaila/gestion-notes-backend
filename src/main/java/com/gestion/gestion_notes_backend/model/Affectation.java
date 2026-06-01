package com.gestion.gestion_notes_backend.model;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "affectations", uniqueConstraints = @UniqueConstraint(columnNames = {"matiere_id", "classe_id"}))
public class Affectation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "professeur_id", nullable = true)
    @JsonIgnoreProperties({"affectations"})
    private Professeur professeur;

    @ManyToOne
    @JoinColumn(name = "matiere_id")
    @JsonIgnoreProperties({"affectations"})
    private Matiere matiere;

    @ManyToOne
    @JoinColumn(name = "classe_id")
    @JsonIgnoreProperties({"affectations"})
    private Classe classe;

    @ManyToOne
    @JoinColumn(name = "annee_scolaire_id")
    @JsonIgnoreProperties({"affectations"})
    private AnneeScolaire anneeScolaire;


    // Getters et Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public AnneeScolaire getAnneeScolaire() { return anneeScolaire; }
    public void setAnneeScolaire(AnneeScolaire anneeScolaire) { this.anneeScolaire = anneeScolaire; }

    public Professeur getProfesseur() { return professeur; }
    public void setProfesseur(Professeur professeur) { this.professeur = professeur; }

    public Matiere getMatiere() { return matiere; }
    public void setMatiere(Matiere matiere) { this.matiere = matiere; }

    public Classe getClasse() { return classe; }
    public void setClasse(Classe classe) { this.classe = classe; }
}