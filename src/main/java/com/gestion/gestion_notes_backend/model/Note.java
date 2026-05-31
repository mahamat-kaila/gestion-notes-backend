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

    private Double devoir1;
    private Double devoir2;
    private Double composition;
    private Double noteUnique;
    private LocalDate dateNote;

    @Enumerated(EnumType.STRING)
    private Trimestre trimestre;

    @ManyToOne
    @JoinColumn(name = "eleve_id")
    @JsonIgnoreProperties({"notes"})
    private Eleve eleve;

    @ManyToOne
    @JoinColumn(name = "matiere_id")
    @JsonIgnoreProperties({"notes"})
    private Matiere matiere;

    // Calculs automatiques
    public Double getMoyenneDevoirs() {
        if (noteUnique != null) return noteUnique;
        if (devoir1 == null || devoir2 == null) return null;
        return Math.round(((devoir1 + devoir2) / 2) * 100.0) / 100.0;
    }

    public Double getMoyenneComposition() {
        if (noteUnique != null) return noteUnique;
        return composition;
    }

    public Double getMoyenneGenerale() {
        if (noteUnique != null) return noteUnique;
        if (getMoyenneDevoirs() == null || composition == null) return null;
        return Math.round(((getMoyenneDevoirs() + composition) / 2) * 100.0) / 100.0;
    }

    public String getAppreciation() {
        Double moy = getMoyenneGenerale();
        if (moy == null) return "-";
        if (moy >= 16) return "Très Bien";
        if (moy >= 14) return "Bien";
        if (moy >= 12) return "Assez Bien";
        if (moy >= 10) return "Passable";
        return "Insuffisant";
    }

    // Getters et Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Double getDevoir1() { return devoir1; }
    public void setDevoir1(Double devoir1) { this.devoir1 = devoir1; }

    public Double getDevoir2() { return devoir2; }
    public void setDevoir2(Double devoir2) { this.devoir2 = devoir2; }

    public Double getComposition() { return composition; }
    public void setComposition(Double composition) { this.composition = composition; }

    public Double getNoteUnique() { return noteUnique; }
    public void setNoteUnique(Double noteUnique) { this.noteUnique = noteUnique; }

    public LocalDate getDateNote() { return dateNote; }
    public void setDateNote(LocalDate dateNote) { this.dateNote = dateNote; }

    public Trimestre getTrimestre() { return trimestre; }
    public void setTrimestre(Trimestre trimestre) { this.trimestre = trimestre; }

    public Eleve getEleve() { return eleve; }
    public void setEleve(Eleve eleve) { this.eleve = eleve; }

    public Matiere getMatiere() { return matiere; }
    public void setMatiere(Matiere matiere) { this.matiere = matiere; }
}