package com.gestion.gestion_notes_backend.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "logs_impressions")
public class LogImpression {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String emailUtilisateur;
    private String nomUtilisateur;
    private String nomEleve;
    private String matriculeEleve;
    private String trimestre;
    private LocalDateTime dateImpression;

    // Getters et Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getEmailUtilisateur() { return emailUtilisateur; }
    public void setEmailUtilisateur(String emailUtilisateur) { this.emailUtilisateur = emailUtilisateur; }

    public String getNomUtilisateur() { return nomUtilisateur; }
    public void setNomUtilisateur(String nomUtilisateur) { this.nomUtilisateur = nomUtilisateur; }

    public String getNomEleve() { return nomEleve; }
    public void setNomEleve(String nomEleve) { this.nomEleve = nomEleve; }

    public String getMatriculeEleve() { return matriculeEleve; }
    public void setMatriculeEleve(String matriculeEleve) { this.matriculeEleve = matriculeEleve; }

    public String getTrimestre() { return trimestre; }
    public void setTrimestre(String trimestre) { this.trimestre = trimestre; }

    public LocalDateTime getDateImpression() { return dateImpression; }
    public void setDateImpression(LocalDateTime dateImpression) { this.dateImpression = dateImpression; }
}