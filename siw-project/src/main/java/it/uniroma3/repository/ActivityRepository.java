package it.uniroma3.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.model.Activity;
import it.uniroma3.model.Facility;


public interface ActivityRepository extends CrudRepository<Activity, Long> {

	List<Activity> findByName(String name);

	List<Activity> findByFacility(Facility facility);


}
