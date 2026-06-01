package com.gestion.gestion_notes_backend.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
@Table(name = "classes", uniqueConstraints = @UniqueConstraint(columnNames = "nom"))
public class Classe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;



    private String nom;
    private Integer effectif;


    // Getters et Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }


    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public Integer getEffectif() { return effectif; }
    public void setEffectif(Integer effectif) { this.effectif = effectif; }
}