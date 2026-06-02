package com.gestion.gestion_notes_backend.repository;

import com.gestion.gestion_notes_backend.model.LogImpression;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface LogImpressionRepository extends JpaRepository<LogImpression, Long> {
    List<LogImpression> findAllByOrderByDateImpressionDesc();
}