package it.uniroma3.repository;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.model.Activity;


public interface ActivityRepository extends CrudRepository<Activity, Long> {

}
