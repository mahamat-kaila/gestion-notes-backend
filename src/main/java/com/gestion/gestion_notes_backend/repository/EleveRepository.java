package com.gestion.gestion_notes_backend.repository;

import com.gestion.gestion_notes_backend.model.Eleve;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

@Repository
public interface EleveRepository extends JpaRepository<Eleve, Long> {
    Eleve findByMatricule(String matricule);
    long countByClasseId(Long classeId);
    @Query("SELECT e FROM Eleve e ORDER BY e.id DESC LIMIT 1")
    Eleve findLastEleve();
    List<Eleve> findByAnneeScolaireId(Long anneeId);
    List<Eleve> findByClasseId(Long classeId);
    long countByClasseIdAndAnneeScolaireId(Long classeId, Long anneeId);
}