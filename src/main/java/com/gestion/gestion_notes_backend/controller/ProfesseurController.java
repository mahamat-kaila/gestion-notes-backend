package com.gestion.gestion_notes_backend.controller;

import com.gestion.gestion_notes_backend.model.Professeur;
import com.gestion.gestion_notes_backend.service.ProfesseurService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/professeurs")
public class ProfesseurController {

    private final ProfesseurService professeurService;

    public ProfesseurController(ProfesseurService professeurService) {
        this.professeurService = professeurService;
    }

    @GetMapping
    public List<Professeur> getAllProfesseurs() {
        return professeurService.getAllProfesseurs();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Professeur> getProfesseurById(@PathVariable Long id) {
        Professeur professeur = professeurService.getProfesseurById(id);
        if (professeur == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(professeur);
    }

    @PostMapping
    public ResponseEntity<?> createProfesseur(@RequestBody Professeur professeur) {
        try {
            return ResponseEntity.ok(professeurService.saveProfesseur(professeur));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateProfesseur(@PathVariable Long id, @RequestBody Professeur professeur) {
        if (professeurService.getProfesseurById(id) == null) return ResponseEntity.notFound().build();
        professeur.setId(id);
        try {
            return ResponseEntity.ok(professeurService.saveProfesseur(professeur));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProfesseur(@PathVariable Long id) {
        if (professeurService.getProfesseurById(id) == null) return ResponseEntity.notFound().build();
        professeurService.deleteProfesseur(id);
        return ResponseEntity.noContent().build();
    }
}