package com.gestion.gestion_notes_backend.repository;

import com.gestion.gestion_notes_backend.model.Matiere;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MatiereRepository extends JpaRepository<Matiere, Long> {
    Matiere findByNom(String nom);
}