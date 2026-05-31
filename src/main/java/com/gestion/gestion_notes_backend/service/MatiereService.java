package com.gestion.gestion_notes_backend.service;

import com.gestion.gestion_notes_backend.model.Matiere;
import com.gestion.gestion_notes_backend.repository.MatiereRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MatiereService {

    private final MatiereRepository matiereRepository;

    public MatiereService(MatiereRepository matiereRepository) {
        this.matiereRepository = matiereRepository;
    }

    public List<Matiere> getAllMatieres() {
        return matiereRepository.findAll();
    }

    public Matiere getMatiereById(Long id) {
        return matiereRepository.findById(id).orElse(null);
    }

    public Matiere saveMatiere(Matiere matiere) {
        Matiere existing = matiereRepository.findByNom(matiere.getNom());
        if (existing != null) {
            throw new RuntimeException("Une matière avec ce nom existe déjà !");
        }
        return matiereRepository.save(matiere);
    }

    public void deleteMatiere(Long id) {
        matiereRepository.deleteById(id);
    }
}