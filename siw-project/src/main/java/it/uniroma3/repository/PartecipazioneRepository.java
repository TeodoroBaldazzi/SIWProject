package it.uniroma3.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.model.Activity;
import it.uniroma3.model.Partecipazione;
import it.uniroma3.model.Student;

public interface PartecipazioneRepository extends CrudRepository<Partecipazione, Long>{

	List<Partecipazione> findByAllievoAndAttivita(Student allievo, Activity attivita);

}
