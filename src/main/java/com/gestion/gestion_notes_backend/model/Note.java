package com.gestion.gestion_notes_backend.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "notes")
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "matiere_id")
    @JsonIgnoreProperties({"notes"})
    private Matiere matiere;    private Double valeur;
    private LocalDate dateNote;
    @Enumerated(EnumType.STRING)
    private Trimestre trimestre;

    @ManyToOne
    @JoinColumn(name = "eleve_id")
    @JsonIgnoreProperties({"notes"})
    private Eleve eleve;

    // Getters et Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Matiere getMatiere() { return matiere; }
    public void setMatiere(Matiere matiere) { this.matiere = matiere; }

    public Double getValeur() { return valeur; }
    public void setValeur(Double valeur) { this.valeur = valeur; }

    public LocalDate getDateNote() { return dateNote; }
    public void setDateNote(LocalDate dateNote) { this.dateNote = dateNote; }

    public Trimestre getTrimestre() { return trimestre; }
    public void setTrimestre(Trimestre trimestre) { this.trimestre = trimestre; }
    public Eleve getEleve() { return eleve; }
    public void setEleve(Eleve eleve) { this.eleve = eleve; }
}