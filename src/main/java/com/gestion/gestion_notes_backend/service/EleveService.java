package com.gestion.gestion_notes_backend.service;

import com.gestion.gestion_notes_backend.model.Classe;
import com.gestion.gestion_notes_backend.model.Eleve;
import com.gestion.gestion_notes_backend.repository.ClasseRepository;
import com.gestion.gestion_notes_backend.repository.EleveRepository;
import com.gestion.gestion_notes_backend.repository.NoteRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EleveService {

    private final EleveRepository eleveRepository;
    private final ClasseRepository classeRepository;
    private final NoteRepository noteRepository;

    public EleveService(EleveRepository eleveRepository, ClasseRepository classeRepository, NoteRepository noteRepository) {
        this.eleveRepository = eleveRepository;
        this.classeRepository = classeRepository;
        this.noteRepository = noteRepository;
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
        String annee = String.valueOf(java.time.Year.now().getValue());
        Eleve dernierEleve = eleveRepository.findLastEleve();
        int numero = 1;
        if (dernierEleve != null && dernierEleve.getMatricule() != null) {
            String dernierMatricule = dernierEleve.getMatricule();
            try {
                numero = Integer.parseInt(dernierMatricule.substring(6)) + 1;
            } catch (Exception e) {
                numero = 1;
            }
        }
        String matricule = annee + "SB" + String.format("%03d", numero);
        // Calculer l'âge
        if (eleve.getDateNaissance() != null) {
            int age = java.time.Period.between(
                    eleve.getDateNaissance(),
                    java.time.LocalDate.now()
            ).getYears();
            eleve.setAge(age);
        }
        eleve.setMatricule(matricule);

        Eleve savedEleve = eleveRepository.save(eleve);

        // Mettre à jour l'effectif de la classe
        if (savedEleve.getClasse() != null) {
            Classe classe = classeRepository.findById(savedEleve.getClasse().getId()).orElse(null);
            if (classe != null) {
                long effectif = eleveRepository.countByClasseId(classe.getId());
                classe.setEffectif((int) effectif);
                classeRepository.save(classe);
            }
        }

        return savedEleve;
    }

    public void deleteEleve(Long id) {
        Eleve eleve = eleveRepository.findById(id).orElse(null);
        if (eleve != null) {
            Classe classe = eleve.getClasse();
            // Supprimer d'abord les notes de l'élève
            noteRepository.deleteAll(noteRepository.findByEleveId(id));
            eleveRepository.deleteById(id);
            // Mettre à jour l'effectif
            if (classe != null) {
                long effectif = eleveRepository.countByClasseId(classe.getId());
                classe.setEffectif((int) effectif);
                classeRepository.save(classe);
            }
        }
    }
}