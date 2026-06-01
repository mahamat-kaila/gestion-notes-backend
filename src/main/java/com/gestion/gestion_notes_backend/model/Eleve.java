package com.gestion.gestion_notes_backend.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "eleves", uniqueConstraints = @UniqueConstraint(columnNames = "matricule"))
public class Eleve {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer age;
    private String matricule;
    private String nom;
    private String prenom;
    private LocalDate dateNaissance;
    private String lieuNaissance;
    private String numeroParent;
    @ManyToOne
    @JoinColumn(name = "classe_id")
    @JsonIgnoreProperties({"eleves"})
    private Classe classe;

    @ManyToOne
    @JoinColumn(name = "annee_scolaire_id")
    @JsonIgnoreProperties({"eleves"})
    private AnneeScolaire anneeScolaire;

    // Getters et Setters

    public AnneeScolaire getAnneeScolaire() { return anneeScolaire; }
    public void setAnneeScolaire(AnneeScolaire anneeScolaire) { this.anneeScolaire = anneeScolaire; }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Integer getAge() { return age; }
    public void setAge(Integer age) { this.age = age; }

    public String getMatricule() { return matricule; }
    public void setMatricule(String matricule) { this.matricule = matricule; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public String getPrenom() { return prenom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }

    public LocalDate getDateNaissance() { return dateNaissance; }
    public void setDateNaissance(LocalDate dateNaissance) { this.dateNaissance = dateNaissance; }

    public String getLieuNaissance() { return lieuNaissance; }
    public void setLieuNaissance(String lieuNaissance) { this.lieuNaissance = lieuNaissance; }

    public String getNumeroParent() { return numeroParent; }
    public void setNumeroParent(String numeroParent) { this.numeroParent = numeroParent; }

    public Classe getClasse() { return classe; }
    public void setClasse(Classe classe) { this.classe = classe; }
}