package com.gestion.gestion_notes_backend.repository;

import com.gestion.gestion_notes_backend.model.Affectation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AffectationRepository extends JpaRepository<Affectation, Long> {
    List<Affectation> findByProfesseurId(Long professeurId);
    List<Affectation> findByClasseId(Long classeId);
    Affectation findByMatiereIdAndClasseId(Long matiereId, Long classeId);
    List<Affectation> findByClasseIdAndAnneeScolaireId(Long classeId, Long anneeId);
    List<Affectation> findByProfesseurIdAndAnneeScolaireId(Long professeurId, Long anneeId);
    Affectation findByMatiereIdAndClasseIdAndAnneeScolaireId(Long matiereId, Long classeId, Long anneeId);

    List<Affectation> findByAnneeScolaireId(Long anneeId);
}