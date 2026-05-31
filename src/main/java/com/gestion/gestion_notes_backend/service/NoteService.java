package com.gestion.gestion_notes_backend.service;

import com.gestion.gestion_notes_backend.model.Note;
import com.gestion.gestion_notes_backend.model.Trimestre;
import com.gestion.gestion_notes_backend.repository.NoteRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class NoteService {

    private final NoteRepository noteRepository;

    public NoteService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    public List<Note> getAllNotes() {
        return noteRepository.findAll();
    }

    public List<Note> getNotesByEleve(Long eleveId) {
        return noteRepository.findByEleveId(eleveId);
    }

    public List<Note> getNotesByEleveAndTrimestre(Long eleveId, Trimestre trimestre) {
        return noteRepository.findByEleveIdAndTrimestre(eleveId, trimestre);
    }

    public Note saveNote(Note note) {
        if (note.getValeur() < 0 || note.getValeur() > 20) {
            throw new RuntimeException("La note doit être comprise entre 0 et 20 !");
        }
        return noteRepository.save(note);
    }

    public void deleteNote(Long id) {
        noteRepository.deleteById(id);
    }

    public Double calculerMoyenne(Long eleveId, Trimestre trimestre) {
        List<Note> notes = noteRepository.findByEleveIdAndTrimestre(eleveId, trimestre);
        if (notes.isEmpty()) return 0.0;

        double sommeNoteCoeff = 0.0;
        double sommeCoeff = 0.0;

        for (Note note : notes) {
            double coeff = note.getMatiere().getCoefficient();
            sommeNoteCoeff += note.getValeur() * coeff;
            sommeCoeff += coeff;
        }

        return Math.round((sommeNoteCoeff / sommeCoeff) * 100.0) / 100.0;
    }
}