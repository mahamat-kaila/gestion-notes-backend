package com.gestion.gestion_notes_backend.service;

import com.gestion.gestion_notes_backend.model.Professeur;
import com.gestion.gestion_notes_backend.repository.ProfesseurRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProfesseurService {

    private final ProfesseurRepository professeurRepository;

    public ProfesseurService(ProfesseurRepository professeurRepository) {
        this.professeurRepository = professeurRepository;
    }

    public List<Professeur> getAllProfesseurs() {
        return professeurRepository.findAll();
    }

    public Professeur getProfesseurById(Long id) {
        return professeurRepository.findById(id).orElse(null);
    }

    public Professeur saveProfesseur(Professeur professeur) {
        Professeur existing = professeurRepository.findByEmail(professeur.getEmail());
        if (existing != null && !existing.getId().equals(professeur.getId())) {
            throw new RuntimeException("Un professeur avec cet email existe déjà !");
        }
        return professeurRepository.save(professeur);
    }

    public void deleteProfesseur(Long id) {
        professeurRepository.deleteById(id);
    }
}