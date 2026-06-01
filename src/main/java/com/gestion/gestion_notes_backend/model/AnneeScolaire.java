package com.gestion.gestion_notes_backend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "annees_scolaires")
public class AnneeScolaire {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String libelle;
    private String dateDebut;
    private String dateFin;
    private Boolean active;

    // Getters et Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getLibelle() { return libelle; }
    public void setLibelle(String libelle) { this.libelle = libelle; }

    public String getDateDebut() { return dateDebut; }
    public void setDateDebut(String dateDebut) { this.dateDebut = dateDebut; }

    public String getDateFin() { return dateFin; }
    public void setDateFin(String dateFin) { this.dateFin = dateFin; }

    public Boolean getActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }
}