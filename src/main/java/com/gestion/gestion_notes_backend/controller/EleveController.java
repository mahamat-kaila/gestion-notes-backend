package com.gestion.gestion_notes_backend.controller;

import com.gestion.gestion_notes_backend.model.Eleve;
import com.gestion.gestion_notes_backend.service.EleveService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/eleves")
public class EleveController {

    private final EleveService eleveService;

    public EleveController(EleveService eleveService) {
        this.eleveService = eleveService;
    }

    @GetMapping
    public List<Eleve> getAllEleves() {
        return eleveService.getAllEleves();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Eleve> getEleveById(@PathVariable Long id) {
        Eleve eleve = eleveService.getEleveById(id);
        if (eleve == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(eleve);
    }

    @PostMapping
    public Eleve createEleve(@RequestBody Eleve eleve) {
        return eleveService.saveEleve(eleve);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Eleve> updateEleve(@PathVariable Long id, @RequestBody Eleve eleve) {
        if (eleveService.getEleveById(id) == null) return ResponseEntity.notFound().build();
        eleve.setId(id);
        return ResponseEntity.ok(eleveService.saveEleve(eleve));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEleve(@PathVariable Long id) {
        if (eleveService.getEleveById(id) == null) return ResponseEntity.notFound().build();
        eleveService.deleteEleve(id);
        return ResponseEntity.noContent().build();
    }
}