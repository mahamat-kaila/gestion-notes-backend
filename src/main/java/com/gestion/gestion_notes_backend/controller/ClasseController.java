package com.gestion.gestion_notes_backend.controller;

import com.gestion.gestion_notes_backend.model.Classe;
import com.gestion.gestion_notes_backend.service.ClasseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/classes")
public class ClasseController {

    private final ClasseService classeService;

    public ClasseController(ClasseService classeService) {
        this.classeService = classeService;
    }

    @GetMapping
    public List<Classe> getAllClasses() {
        return classeService.getAllClasses();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Classe> getClasseById(@PathVariable Long id) {
        Classe classe = classeService.getClasseById(id);
        if (classe == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(classe);
    }

    @PostMapping
    public Classe createClasse(@RequestBody Classe classe) {
        return classeService.saveClasse(classe);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Classe> updateClasse(@PathVariable Long id, @RequestBody Classe classe) {
        if (classeService.getClasseById(id) == null) return ResponseEntity.notFound().build();
        classe.setId(id);
        return ResponseEntity.ok(classeService.saveClasse(classe));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClasse(@PathVariable Long id) {
        if (classeService.getClasseById(id) == null) return ResponseEntity.notFound().build();
        classeService.deleteClasse(id);
        return ResponseEntity.noContent().build();
    }
}