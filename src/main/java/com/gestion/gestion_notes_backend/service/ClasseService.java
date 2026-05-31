package com.gestion.gestion_notes_backend.service;

import com.gestion.gestion_notes_backend.model.Classe;
import com.gestion.gestion_notes_backend.repository.ClasseRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ClasseService {

    private final ClasseRepository classeRepository;

    public ClasseService(ClasseRepository classeRepository) {
        this.classeRepository = classeRepository;
    }

    public List<Classe> getAllClasses() {
        return classeRepository.findAll();
    }

    public Classe getClasseById(Long id) {
        return classeRepository.findById(id).orElse(null);
    }

    public Classe saveClasse(Classe classe) {
        Classe existing = classeRepository.findByNom(classe.getNom());
        if (existing != null) {
            throw new RuntimeException("Une classe avec ce nom existe déjà !");
        }
        return classeRepository.save(classe);
    }

    public void deleteClasse(Long id) {
        classeRepository.deleteById(id);
    }
}