package com.gestion.gestion_notes_backend.service;

import com.gestion.gestion_notes_backend.model.Affectation;
import com.gestion.gestion_notes_backend.repository.AffectationRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AffectationService {

    private final AffectationRepository affectationRepository;

    public AffectationService(AffectationRepository affectationRepository) {
        this.affectationRepository = affectationRepository;
    }

    public List<Affectation> getAllAffectations() {
        return affectationRepository.findAll();
    }

    public List<Affectation> getAffectationsByProfesseur(Long professeurId) {
        return affectationRepository.findByProfesseurId(professeurId);
    }

    public List<Affectation> getAffectationsByClasse(Long classeId) {
        return affectationRepository.findByClasseId(classeId);
    }

    public Affectation saveAffectation(Affectation affectation) {
        Affectation existing = affectationRepository.findByMatiereIdAndClasseId(
                affectation.getMatiere().getId(),
                affectation.getClasse().getId()
        );
        if (existing != null) {
            throw new RuntimeException("Cette matière est déjà attribuée à un professeur dans cette classe !");
        }
        return affectationRepository.save(affectation);
    }

    public void deleteAffectation(Long id) {
        affectationRepository.deleteById(id);
    }
}