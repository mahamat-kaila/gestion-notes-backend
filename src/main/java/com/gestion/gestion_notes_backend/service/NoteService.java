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
        return noteRepository.save(note);
    }

    public void deleteNote(Long id) {
        noteRepository.deleteById(id);
    }
}