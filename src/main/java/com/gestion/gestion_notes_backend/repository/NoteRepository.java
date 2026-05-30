package com.gestion.gestion_notes_backend.repository;

import com.gestion.gestion_notes_backend.model.Note;
import com.gestion.gestion_notes_backend.model.Trimestre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {
    List<Note> findByEleveId(Long eleveId);
    List<Note> findByEleveIdAndTrimestre(Long eleveId, Trimestre trimestre);
}