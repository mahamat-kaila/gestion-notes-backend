package com.gestion.gestion_notes_backend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "utilisateurs")
public class Utilisateur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private String prenom;
    private String email;
    private String motDePasse;
    private String role; // ADMIN ou DIRECTEUR
    private Boolean actif;
    private Boolean peutSaisirNotes;
    private java.time.LocalDateTime derniereConnexion;

    public java.time.LocalDateTime getDerniereConnexion() { return derniereConnexion; }
    public void setDerniereConnexion(java.time.LocalDateTime derniereConnexion) { this.derniereConnexion = derniereConnexion; }

    // Getters et Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public String getPrenom() { return prenom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getMotDePasse() { return motDePasse; }
    public void setMotDePasse(String motDePasse) { this.motDePasse = motDePasse; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public Boolean getActif() { return actif; }
    public void setActif(Boolean actif) { this.actif = actif; }

    public Boolean getPeutSaisirNotes() { return peutSaisirNotes; }
    public void setPeutSaisirNotes(Boolean peutSaisirNotes) { this.peutSaisirNotes = peutSaisirNotes; }
}