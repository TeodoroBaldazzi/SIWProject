package it.uniroma3.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.model.Facility;

public interface FacilityRepository extends CrudRepository<Facility, Long> {

	List<Facility> findByNameAndAddressAndEmail(String name, String address, String email);

}
