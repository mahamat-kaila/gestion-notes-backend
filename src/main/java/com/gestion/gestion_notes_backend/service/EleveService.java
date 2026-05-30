package com.gestion.gestion_notes_backend.service;

import com.gestion.gestion_notes_backend.model.Eleve;
import com.gestion.gestion_notes_backend.repository.EleveRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EleveService {

    private final EleveRepository eleveRepository;

    public EleveService(EleveRepository eleveRepository) {
        this.eleveRepository = eleveRepository;
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
        return eleveRepository.save(eleve);
    }

    public void deleteEleve(Long id) {
        eleveRepository.deleteById(id);
    }
}