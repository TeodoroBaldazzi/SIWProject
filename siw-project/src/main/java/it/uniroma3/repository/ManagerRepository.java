package it.uniroma3.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.model.FacilityManager;

public interface ManagerRepository extends CrudRepository<FacilityManager, Long> {

	List<FacilityManager> findByUsername(String username);

}
