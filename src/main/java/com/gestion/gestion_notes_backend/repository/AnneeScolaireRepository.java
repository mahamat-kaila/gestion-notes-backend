package com.gestion.gestion_notes_backend.repository;

import com.gestion.gestion_notes_backend.model.AnneeScolaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnneeScolaireRepository extends JpaRepository<AnneeScolaire, Long> {
    AnneeScolaire findByActive(Boolean active);
}