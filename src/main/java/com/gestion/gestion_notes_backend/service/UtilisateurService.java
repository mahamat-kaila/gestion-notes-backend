package com.gestion.gestion_notes_backend.service;

import com.gestion.gestion_notes_backend.model.Utilisateur;
import com.gestion.gestion_notes_backend.repository.UtilisateurRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UtilisateurService {

    private final UtilisateurRepository utilisateurRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UtilisateurService(UtilisateurRepository utilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public List<Utilisateur> getAllUtilisateurs() {
        return utilisateurRepository.findAll();
    }

    public Utilisateur getUtilisateurByEmail(String email) {
        return utilisateurRepository.findByEmail(email);
    }

    public Utilisateur createUtilisateur(Utilisateur utilisateur) {
        utilisateur.setMotDePasse(passwordEncoder.encode(utilisateur.getMotDePasse()));
        utilisateur.setActif(true);
        utilisateur.setPeutSaisirNotes(false);
        return utilisateurRepository.save(utilisateur);
    }

    public boolean verifierMotDePasse(String motDePasse, String motDePasseEncode) {
        return passwordEncoder.matches(motDePasse, motDePasseEncode);
    }

    public Utilisateur toggleSaisirNotes(Long id) {
        Utilisateur utilisateur = utilisateurRepository.findById(id).orElse(null);
        if (utilisateur != null) {
            utilisateur.setPeutSaisirNotes(!utilisateur.getPeutSaisirNotes());
            return utilisateurRepository.save(utilisateur);
        }
        return null;
    }

    public Utilisateur toggleActif(Long id) {
        Utilisateur utilisateur = utilisateurRepository.findById(id).orElse(null);
        if (utilisateur != null) {
            utilisateur.setActif(!utilisateur.getActif());
            return utilisateurRepository.save(utilisateur);
        }
        return null;
    }

    public void deleteUtilisateur(Long id) {
        utilisateurRepository.deleteById(id);
    }
}