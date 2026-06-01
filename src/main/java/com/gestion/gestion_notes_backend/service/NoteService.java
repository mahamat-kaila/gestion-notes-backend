package com.gestion.gestion_notes_backend.service;

import com.gestion.gestion_notes_backend.model.*;
import com.gestion.gestion_notes_backend.repository.*;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class NoteService {

    private final NoteRepository noteRepository;
    private final AnneeScolaireRepository anneeScolaireRepository;

    public NoteService(NoteRepository noteRepository, AnneeScolaireRepository anneeScolaireRepository) {
        this.noteRepository = noteRepository;
        this.anneeScolaireRepository = anneeScolaireRepository;
    }

    public List<Note> getAllNotes() {
        return noteRepository.findAll();
    }

    public List<Note> getNotesByEleve(Long eleveId) {
        AnneeScolaire anneeActive = anneeScolaireRepository.findByActive(true);
        if (anneeActive == null) return noteRepository.findByEleveId(eleveId);
        return noteRepository.findByEleveIdAndAnneeScolaireId(eleveId, anneeActive.getId());
    }

    public List<Note> getNotesByEleveAndTrimestre(Long eleveId, Trimestre trimestre) {
        AnneeScolaire anneeActive = anneeScolaireRepository.findByActive(true);
        if (anneeActive == null) return noteRepository.findByEleveIdAndTrimestre(eleveId, trimestre);
        return noteRepository.findByEleveIdAndTrimestreAndAnneeScolaireId(eleveId, trimestre, anneeActive.getId());
    }

    public Note saveNote(Note note) {
        if (note.getDevoir1() != null && (note.getDevoir1() < 0 || note.getDevoir1() > 20))
            throw new RuntimeException("Le devoir 1 doit être entre 0 et 20 !");
        if (note.getDevoir2() != null && (note.getDevoir2() < 0 || note.getDevoir2() > 20))
            throw new RuntimeException("Le devoir 2 doit être entre 0 et 20 !");
        if (note.getComposition() != null && (note.getComposition() < 0 || note.getComposition() > 20))
            throw new RuntimeException("La composition doit être entre 0 et 20 !");

        // Associer l'année scolaire active
        AnneeScolaire anneeActive = anneeScolaireRepository.findByActive(true);
        if (anneeActive != null) note.setAnneeScolaire(anneeActive);

        return noteRepository.save(note);
    }

    public void deleteNote(Long id) {
        noteRepository.deleteById(id);
    }

    public Double calculerMoyenneGenerale(Long eleveId, Trimestre trimestre) {
        List<Note> notes = getNotesByEleveAndTrimestre(eleveId, trimestre);
        if (notes.isEmpty()) return 0.0;

        double sommeCoeffMoyenne = 0.0;
        double sommeCoeff = 0.0;

        for (Note note : notes) {
            Double moyenneGenerale = note.getMoyenneGenerale();
            if (moyenneGenerale != null && note.getMatiere() != null) {
                double coeff = note.getMatiere().getCoefficient();
                sommeCoeffMoyenne += moyenneGenerale * coeff;
                sommeCoeff += coeff;
            }
        }

        if (sommeCoeff == 0) return 0.0;
        return Math.round((sommeCoeffMoyenne / sommeCoeff) * 100.0) / 100.0;
    }

    public int calculerRang(Long eleveId, Trimestre trimestre, Long classeId) {
        AnneeScolaire anneeActive = anneeScolaireRepository.findByActive(true);
        List<Note> toutesNotes = noteRepository.findAll();

        java.util.Map<Long, Double> moyennesEleves = new java.util.HashMap<>();

        toutesNotes.stream()
                .filter(n -> n.getTrimestre() == trimestre
                        && n.getEleve() != null
                        && n.getEleve().getClasse() != null
                        && n.getEleve().getClasse().getId().equals(classeId)
                        && (anneeActive == null || (n.getAnneeScolaire() != null && n.getAnneeScolaire().getId().equals(anneeActive.getId()))))
                .forEach(n -> {
                    Long id = n.getEleve().getId();
                    moyennesEleves.put(id, calculerMoyenneGenerale(id, trimestre));
                });

        if (!moyennesEleves.containsKey(eleveId)) return 0;

        double moyenneEleve = moyennesEleves.get(eleveId);
        int rang = 1;
        for (double moyenne : moyennesEleves.values()) {
            if (moyenne > moyenneEleve) rang++;
        }
        return rang;
    }
}