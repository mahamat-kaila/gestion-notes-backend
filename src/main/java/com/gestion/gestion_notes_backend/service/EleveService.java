package com.gestion.gestion_notes_backend.service;

import com.gestion.gestion_notes_backend.model.Classe;
import com.gestion.gestion_notes_backend.model.Eleve;
import com.gestion.gestion_notes_backend.repository.ClasseRepository;
import com.gestion.gestion_notes_backend.repository.EleveRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EleveService {

    private final EleveRepository eleveRepository;
    private final ClasseRepository classeRepository;

    public EleveService(EleveRepository eleveRepository, ClasseRepository classeRepository) {
        this.eleveRepository = eleveRepository;
        this.classeRepository = classeRepository;
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
            eleveRepository.deleteById(id);

            // Mettre à jour l'effectif après suppression
            if (classe != null) {
                long effectif = eleveRepository.countByClasseId(classe.getId());
                classe.setEffectif((int) effectif);
                classeRepository.save(classe);
            }
        }
    }
}