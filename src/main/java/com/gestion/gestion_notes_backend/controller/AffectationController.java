package com.gestion.gestion_notes_backend.controller;

import com.gestion.gestion_notes_backend.model.Affectation;
import com.gestion.gestion_notes_backend.service.AffectationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/affectations")
public class AffectationController {

    private final AffectationService affectationService;

    public AffectationController(AffectationService affectationService) {
        this.affectationService = affectationService;
    }

    @GetMapping
    public List<Affectation> getAllAffectations() {
        return affectationService.getAllAffectations();
    }

    @GetMapping("/professeur/{professeurId}")
    public List<Affectation> getAffectationsByProfesseur(@PathVariable Long professeurId) {
        return affectationService.getAffectationsByProfesseur(professeurId);
    }

    @GetMapping("/classe/{classeId}")
    public List<Affectation> getAffectationsByClasse(@PathVariable Long classeId) {
        return affectationService.getAffectationsByClasse(classeId);
    }

    @PostMapping
    public ResponseEntity<?> createAffectation(@RequestBody Affectation affectation) {
        try {
            return ResponseEntity.ok(affectationService.saveAffectation(affectation));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAffectation(@PathVariable Long id) {
        affectationService.deleteAffectation(id);
        return ResponseEntity.noContent().build();
    }
}