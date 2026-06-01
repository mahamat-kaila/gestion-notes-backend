package com.gestion.gestion_notes_backend.service;

import com.gestion.gestion_notes_backend.model.AnneeScolaire;
import com.gestion.gestion_notes_backend.repository.*;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AnneeScolaireService {

    private final AnneeScolaireRepository anneeScolaireRepository;
    private final EleveRepository eleveRepository;
    private final NoteRepository noteRepository;
    private final AffectationRepository affectationRepository;

    public AnneeScolaireService(AnneeScolaireRepository anneeScolaireRepository,
                                EleveRepository eleveRepository,
                                NoteRepository noteRepository,
                                AffectationRepository affectationRepository) {
        this.anneeScolaireRepository = anneeScolaireRepository;
        this.eleveRepository = eleveRepository;
        this.noteRepository = noteRepository;
        this.affectationRepository = affectationRepository;
    }

    public List<AnneeScolaire> getAllAnnees() {
        return anneeScolaireRepository.findAll();
    }

    public AnneeScolaire getAnneeActive() {
        return anneeScolaireRepository.findByActive(true);
    }

    public AnneeScolaire saveAnnee(AnneeScolaire annee) {
        return anneeScolaireRepository.save(annee);
    }

    public AnneeScolaire activerAnnee(Long id) {
        List<AnneeScolaire> annees = anneeScolaireRepository.findAll();
        for (AnneeScolaire a : annees) {
            a.setActive(false);
            anneeScolaireRepository.save(a);
        }
        AnneeScolaire annee = anneeScolaireRepository.findById(id).orElse(null);
        if (annee != null) {
            annee.setActive(true);
            return anneeScolaireRepository.save(annee);
        }
        return null;
    }

    public void deleteAnnee(Long id) {
        AnneeScolaire annee = anneeScolaireRepository.findById(id).orElse(null);
        if (annee == null) return;
        if (annee.getActive()) {
            throw new RuntimeException("Impossible de supprimer l'année active !");
        }

        // 1. Supprimer les notes liées à cette année
        noteRepository.deleteAll(noteRepository.findByAnneeScolaireId(id));

        // 2. Supprimer les affectations liées
        affectationRepository.deleteAll(affectationRepository.findByAnneeScolaireId(id));

        // 3. Supprimer les élèves liés
        eleveRepository.deleteAll(eleveRepository.findByAnneeScolaireId(id));

        // 4. Supprimer l'année
        anneeScolaireRepository.deleteById(id);
    }
}