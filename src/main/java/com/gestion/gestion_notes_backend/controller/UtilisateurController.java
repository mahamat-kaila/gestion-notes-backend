package com.gestion.gestion_notes_backend.controller;

import com.gestion.gestion_notes_backend.model.Utilisateur;
import com.gestion.gestion_notes_backend.service.UtilisateurService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/utilisateurs")
public class UtilisateurController {

    private final UtilisateurService utilisateurService;

    public UtilisateurController(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }

    @GetMapping
    public List<Utilisateur> getAllUtilisateurs() {
        return utilisateurService.getAllUtilisateurs();
    }

    @PostMapping
    public ResponseEntity<?> createUtilisateur(@RequestBody Utilisateur utilisateur) {
        try {
            return ResponseEntity.ok(utilisateurService.createUtilisateur(utilisateur));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erreur lors de la création !");
        }
    }

    @PutMapping("/{id}/toggle-notes")
    public ResponseEntity<?> toggleSaisirNotes(@PathVariable Long id) {
        Utilisateur utilisateur = utilisateurService.toggleSaisirNotes(id);
        if (utilisateur == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(utilisateur);
    }

    @PutMapping("/{id}/toggle-actif")
    public ResponseEntity<?> toggleActif(@PathVariable Long id) {
        Utilisateur utilisateur = utilisateurService.toggleActif(id);
        if (utilisateur == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(utilisateur);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUtilisateur(@PathVariable Long id) {
        utilisateurService.deleteUtilisateur(id);
        return ResponseEntity.noContent().build();
    }
}