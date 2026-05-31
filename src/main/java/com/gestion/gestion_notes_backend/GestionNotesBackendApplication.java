package com.gestion.gestion_notes_backend;

import com.gestion.gestion_notes_backend.model.*;
import com.gestion.gestion_notes_backend.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import java.util.List;

@SpringBootApplication
public class GestionNotesBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestionNotesBackendApplication.class, args);
	}

	@Bean
	CommandLineRunner initConduite(
			MatiereRepository matiereRepository,
			ClasseRepository classeRepository,
			AffectationRepository affectationRepository) {
		return args -> {
			// Créer la matière Conduite si elle n'existe pas
			Matiere conduite = matiereRepository.findByNom("Conduite");
			if (conduite == null) {
				conduite = new Matiere();
				conduite.setNom("Conduite");
				conduite.setCoefficient(1.0);
				conduite = matiereRepository.save(conduite);
			}

			// Affecter Conduite à toutes les classes sans professeur
			List<Classe> classes = classeRepository.findAll();
			for (Classe classe : classes) {
				Affectation existing = affectationRepository.findByMatiereIdAndClasseId(
						conduite.getId(), classe.getId());
				if (existing == null) {
					Affectation affectation = new Affectation();
					affectation.setMatiere(conduite);
					affectation.setClasse(classe);
					affectation.setProfesseur(null);
					affectationRepository.save(affectation);
				}
			}
		};
	}
}