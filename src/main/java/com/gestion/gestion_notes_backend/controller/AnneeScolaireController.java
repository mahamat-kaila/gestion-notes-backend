package com.gestion.gestion_notes_backend.controller;

import com.gestion.gestion_notes_backend.model.AnneeScolaire;
import com.gestion.gestion_notes_backend.service.AnneeScolaireService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/annees")
public class AnneeScolaireController {

    private final AnneeScolaireService anneeScolaireService;

    public AnneeScolaireController(AnneeScolaireService anneeScolaireService) {
        this.anneeScolaireService = anneeScolaireService;
    }

    @GetMapping
    public List<AnneeScolaire> getAllAnnees() {
        return anneeScolaireService.getAllAnnees();
    }

    @GetMapping("/active")
    public ResponseEntity<AnneeScolaire> getAnneeActive() {
        AnneeScolaire annee = anneeScolaireService.getAnneeActive();
        if (annee == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(annee);
    }

    @PostMapping
    public AnneeScolaire createAnnee(@RequestBody AnneeScolaire annee) {
        return anneeScolaireService.saveAnnee(annee);
    }

    @PutMapping("/{id}/activer")
    public ResponseEntity<AnneeScolaire> activerAnnee(@PathVariable Long id) {
        AnneeScolaire annee = anneeScolaireService.activerAnnee(id);
        if (annee == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(annee);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAnnee(@PathVariable Long id) {
        try {
            anneeScolaireService.deleteAnnee(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}