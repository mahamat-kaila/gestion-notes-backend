package com.gestion.gestion_notes_backend.controller;

import com.gestion.gestion_notes_backend.model.Utilisateur;
import com.gestion.gestion_notes_backend.repository.UtilisateurRepository;
import com.gestion.gestion_notes_backend.service.JwtService;
import com.gestion.gestion_notes_backend.service.UtilisateurService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UtilisateurService utilisateurService;
    private final JwtService jwtService;

    private final UtilisateurRepository utilisateurRepository;

    public AuthController(UtilisateurService utilisateurService, JwtService jwtService, UtilisateurRepository utilisateurRepository) {
        this.utilisateurService = utilisateurService;
        this.jwtService = jwtService;
        this.utilisateurRepository = utilisateurRepository;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        String motDePasse = request.get("motDePasse");

        Utilisateur utilisateur = utilisateurService.getUtilisateurByEmail(email);

        // Mettre à jour la dernière connexion
        utilisateur.setDerniereConnexion(java.time.LocalDateTime.now());
        utilisateurRepository.save(utilisateur);
        if (utilisateur == null) {
            return ResponseEntity.badRequest().body("Email ou mot de passe incorrect !");
        }

        if (!utilisateur.getActif()) {
            return ResponseEntity.badRequest().body("Compte désactivé !");
        }

        if (!utilisateurService.verifierMotDePasse(motDePasse, utilisateur.getMotDePasse())) {
            return ResponseEntity.badRequest().body("Email ou mot de passe incorrect !");
        }

        String token = jwtService.generateToken(utilisateur.getEmail(), utilisateur.getRole());

        Map<String, Object> response = new HashMap<>();
        response.put("token", token);
        response.put("role", utilisateur.getRole());
        response.put("nom", utilisateur.getNom());
        response.put("prenom", utilisateur.getPrenom());
        response.put("peutSaisirNotes", utilisateur.getPeutSaisirNotes());

        return ResponseEntity.ok(response);
    }
}