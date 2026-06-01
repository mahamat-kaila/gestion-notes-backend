package com.gestion.gestion_notes_backend.service;

import com.gestion.gestion_notes_backend.model.*;
import com.gestion.gestion_notes_backend.repository.*;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AffectationService {

    private final AffectationRepository affectationRepository;
    private final AnneeScolaireRepository anneeScolaireRepository;

    public AffectationService(AffectationRepository affectationRepository,
                              AnneeScolaireRepository anneeScolaireRepository) {
        this.affectationRepository = affectationRepository;
        this.anneeScolaireRepository = anneeScolaireRepository;
    }

    public List<Affectation> getAllAffectations() {
        AnneeScolaire anneeActive = anneeScolaireRepository.findByActive(true);
        if (anneeActive == null) return affectationRepository.findAll();
        return affectationRepository.findByAnneeScolaireId(anneeActive.getId());
    }

    public List<Affectation> getAffectationsByProfesseur(Long professeurId) {
        AnneeScolaire anneeActive = anneeScolaireRepository.findByActive(true);
        if (anneeActive == null) return affectationRepository.findByProfesseurId(professeurId);
        return affectationRepository.findByProfesseurIdAndAnneeScolaireId(professeurId, anneeActive.getId());
    }

    public List<Affectation> getAffectationsByClasse(Long classeId) {
        AnneeScolaire anneeActive = anneeScolaireRepository.findByActive(true);
        if (anneeActive == null) return affectationRepository.findByClasseId(classeId);
        return affectationRepository.findByClasseIdAndAnneeScolaireId(classeId, anneeActive.getId());
    }

    public Affectation saveAffectation(Affectation affectation) {
        AnneeScolaire anneeActive = anneeScolaireRepository.findByActive(true);
        if (anneeActive != null) affectation.setAnneeScolaire(anneeActive);

        Affectation existing = anneeActive != null ?
                affectationRepository.findByMatiereIdAndClasseIdAndAnneeScolaireId(
                        affectation.getMatiere().getId(),
                        affectation.getClasse().getId(),
                        anneeActive.getId()) :
                affectationRepository.findByMatiereIdAndClasseId(
                        affectation.getMatiere().getId(),
                        affectation.getClasse().getId());

        if (existing != null) {
            throw new RuntimeException("Cette matière est déjà attribuée à un professeur dans cette classe !");
        }
        return affectationRepository.save(affectation);
    }

    public void deleteAffectation(Long id) {
        affectationRepository.deleteById(id);
    }
}