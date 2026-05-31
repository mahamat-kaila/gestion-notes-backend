package com.gestion.gestion_notes_backend.controller;

import com.gestion.gestion_notes_backend.model.Note;
import com.gestion.gestion_notes_backend.model.Trimestre;
import com.gestion.gestion_notes_backend.service.NoteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/notes")
public class NoteController {

    private final NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping
    public List<Note> getAllNotes() {
        return noteService.getAllNotes();
    }

    @GetMapping("/eleve/{eleveId}")
    public List<Note> getNotesByEleve(@PathVariable Long eleveId) {
        return noteService.getNotesByEleve(eleveId);
    }

    @GetMapping("/eleve/{eleveId}/trimestre/{trimestre}")
    public List<Note> getNotesByEleveAndTrimestre(
            @PathVariable Long eleveId,
            @PathVariable Trimestre trimestre) {
        return noteService.getNotesByEleveAndTrimestre(eleveId, trimestre);
    }

    @PostMapping
    public ResponseEntity<?> createNote(@RequestBody Note note) {
        try {
            return ResponseEntity.ok(noteService.saveNote(note));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNote(@PathVariable Long id) {
        noteService.deleteNote(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/moyenne/eleve/{eleveId}/trimestre/{trimestre}")    public ResponseEntity<Double> getMoyenne(
            @PathVariable Long eleveId,
            @PathVariable Trimestre trimestre) {
        Double moyenne = noteService.calculerMoyenne(eleveId, trimestre);
        return ResponseEntity.ok(moyenne);
    }
}