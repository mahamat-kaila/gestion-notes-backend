package com.gestion.gestion_notes_backend.repository;

import com.gestion.gestion_notes_backend.model.Classe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClasseRepository extends JpaRepository<Classe, Long> {
    Classe findByNom(String nom);
}