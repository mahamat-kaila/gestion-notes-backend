package com.gestion.gestion_notes_backend.repository;

import com.gestion.gestion_notes_backend.model.Professeur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfesseurRepository extends JpaRepository<Professeur, Long> {
    Professeur findByEmail(String email);
}