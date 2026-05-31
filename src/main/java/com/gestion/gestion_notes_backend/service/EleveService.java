package com.gestion.gestion_notes_backend.service;

import com.gestion.gestion_notes_backend.model.*;
import com.gestion.gestion_notes_backend.repository.*;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
public class EleveService {

    private final EleveRepository eleveRepository;
    private final ClasseRepository classeRepository;
    private final NoteRepository noteRepository;
    private final AffectationRepository affectationRepository;

    public EleveService(EleveRepository eleveRepository, ClasseRepository classeRepository,
                        NoteRepository noteRepository, AffectationRepository affectationRepository) {
        this.eleveRepository = eleveRepository;
        this.classeRepository = classeRepository;
        this.noteRepository = noteRepository;
        this.affectationRepository = affectationRepository;
    }

    public List<Eleve> getAllEleves() {
        return eleveRepository.findAll();
    }

    public Eleve getEleveById(Long id) {
        return eleveRepository.findById(id).orElse(null);
    }

    public Eleve getEleveByMatricule(String matricule) {
        return eleveRepository.findByMatricule(matricule);
    }

    public Eleve saveEleve(Eleve eleve) {
        // Générer le matricule automatiquement
        // Générer le matricule automatiquement
        String annee = String.valueOf(java.time.Year.now().getValue());
        Eleve dernierEleve = eleveRepository.findLastEleve();
        int numero = 1;
        if (dernierEleve != null && dernierEleve.getMatricule() != null) {
            try {
                numero = Integer.parseInt(dernierEleve.getMatricule().substring(6)) + 1;
            } catch (Exception e) {
                numero = 1;
            }
        }
// Vérifier que le matricule n'existe pas déjà
        String matricule;
        do {
            matricule = annee + "SB" + String.format("%03d", numero);
            numero++;
        } while (eleveRepository.findByMatricule(matricule) != null);
        eleve.setMatricule(matricule);

        // Calculer l'âge
        if (eleve.getDateNaissance() != null) {
            int age = java.time.Period.between(
                    eleve.getDateNaissance(),
                    java.time.LocalDate.now()
            ).getYears();
            eleve.setAge(age);
        }

        Eleve savedEleve = eleveRepository.save(eleve);

        // Mettre à jour l'effectif de la classe
        if (savedEleve.getClasse() != null) {
            Classe classe = classeRepository.findById(savedEleve.getClasse().getId()).orElse(null);
            if (classe != null) {
                long effectif = eleveRepository.countByClasseId(classe.getId());
                classe.setEffectif((int) effectif);
                classeRepository.save(classe);

                // Créer notes par défaut pour toutes les matières affectées à la classe
                List<Affectation> affectations = affectationRepository.findByClasseId(classe.getId());
                for (Trimestre trimestre : Trimestre.values()) {
                    for (Affectation affectation : affectations) {
                        Note note = new Note();
                        note.setEleve(savedEleve);
                        note.setMatiere(affectation.getMatiere());
                        note.setTrimestre(trimestre);
                        note.setDevoir1(0.0);
                        note.setDevoir2(0.0);
                        note.setComposition(0.0);
                        note.setDateNote(LocalDate.now());
                        noteRepository.save(note);
                    }
                }
            }
        }

        return savedEleve;
    }

    public void deleteEleve(Long id) {
        Eleve eleve = eleveRepository.findById(id).orElse(null);
        if (eleve != null) {
            Classe classe = eleve.getClasse();
            noteRepository.deleteAll(noteRepository.findByEleveId(id));
            eleveRepository.deleteById(id);
            if (classe != null) {
                long effectif = eleveRepository.countByClasseId(classe.getId());
                classe.setEffectif((int) effectif);
                classeRepository.save(classe);
            }
        }
    }
}