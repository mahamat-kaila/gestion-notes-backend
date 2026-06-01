package com.gestion.gestion_notes_backend.service;

import com.gestion.gestion_notes_backend.model.Classe;
import com.gestion.gestion_notes_backend.model.Eleve;
import com.gestion.gestion_notes_backend.repository.AffectationRepository;
import com.gestion.gestion_notes_backend.repository.ClasseRepository;
import com.gestion.gestion_notes_backend.repository.EleveRepository;
import com.gestion.gestion_notes_backend.repository.NoteRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ClasseService {

    private final ClasseRepository classeRepository;
    private final AffectationRepository affectationRepository;

    private final EleveRepository eleveRepository;
    private final NoteRepository noteRepository;

    public ClasseService(ClasseRepository classeRepository, AffectationRepository affectationRepository,
                         EleveRepository eleveRepository, NoteRepository noteRepository) {
        this.classeRepository = classeRepository;
        this.affectationRepository = affectationRepository;
        this.eleveRepository = eleveRepository;
        this.noteRepository = noteRepository;
    }

    public List<Classe> getAllClasses() {
        return classeRepository.findAll();
    }

    public Classe getClasseById(Long id) {
        return classeRepository.findById(id).orElse(null);
    }

    public Classe saveClasse(Classe classe) {
        Classe existing = classeRepository.findByNom(classe.getNom());
        if (existing != null && !existing.getId().equals(classe.getId())) {
            throw new RuntimeException("Une classe avec ce nom existe déjà !");
        }
        return classeRepository.save(classe);
    }

    public void deleteClasse(Long id) {
        // Supprimer les notes des élèves de cette classe
        List<Eleve> eleves = eleveRepository.findByClasseId(id);
        for (Eleve eleve : eleves) {
            noteRepository.deleteAll(noteRepository.findByEleveId(eleve.getId()));
        }
        // Supprimer les élèves
        eleveRepository.deleteAll(eleves);
        // Supprimer les affectations
        affectationRepository.deleteAll(affectationRepository.findByClasseId(id));
        // Supprimer la classe
        classeRepository.deleteById(id);
    }
}