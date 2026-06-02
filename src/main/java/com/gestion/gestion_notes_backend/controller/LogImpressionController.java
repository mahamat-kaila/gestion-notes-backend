package com.gestion.gestion_notes_backend.controller;

import com.gestion.gestion_notes_backend.model.LogImpression;
import com.gestion.gestion_notes_backend.repository.LogImpressionRepository;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/logs")
public class LogImpressionController {

    private final LogImpressionRepository logImpressionRepository;

    public LogImpressionController(LogImpressionRepository logImpressionRepository) {
        this.logImpressionRepository = logImpressionRepository;
    }

    @GetMapping
    public List<LogImpression> getAllLogs() {
        return logImpressionRepository.findAllByOrderByDateImpressionDesc();
    }

    @PostMapping
    public LogImpression createLog(@RequestBody LogImpression log) {
        log.setDateImpression(LocalDateTime.now());
        return logImpressionRepository.save(log);
    }
}