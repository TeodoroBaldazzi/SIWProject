package it.uniroma3.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.model.Activity;
import it.uniroma3.model.Participation;
import it.uniroma3.model.Student;

public interface PartecipazioneRepository extends CrudRepository<Participation, Long>{

	List<Participation> findByAllievoAndAttivita(Student allievo, Activity attivita);

}
