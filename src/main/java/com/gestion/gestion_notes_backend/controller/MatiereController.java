package com.gestion.gestion_notes_backend.controller;

import com.gestion.gestion_notes_backend.model.Matiere;
import com.gestion.gestion_notes_backend.service.MatiereService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/matieres")
public class MatiereController {

    private final MatiereService matiereService;

    public MatiereController(MatiereService matiereService) {
        this.matiereService = matiereService;
    }

    @GetMapping
    public List<Matiere> getAllMatieres() {
        return matiereService.getAllMatieres();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Matiere> getMatiereById(@PathVariable Long id) {
        Matiere matiere = matiereService.getMatiereById(id);
        if (matiere == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(matiere);
    }

    @PostMapping
    public ResponseEntity<?> createMatiere(@RequestBody Matiere matiere) {
        try {
            return ResponseEntity.ok(matiereService.saveMatiere(matiere));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Une matière avec ce nom existe déjà !");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Matiere> updateMatiere(@PathVariable Long id, @RequestBody Matiere matiere) {
        if (matiereService.getMatiereById(id) == null) return ResponseEntity.notFound().build();
        matiere.setId(id);
        return ResponseEntity.ok(matiereService.saveMatiere(matiere));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMatiere(@PathVariable Long id) {
        if (matiereService.getMatiereById(id) == null) return ResponseEntity.notFound().build();
        matiereService.deleteMatiere(id);
        return ResponseEntity.noContent().build();
    }
}