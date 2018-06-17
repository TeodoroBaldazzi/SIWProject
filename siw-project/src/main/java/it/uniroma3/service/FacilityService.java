package it.uniroma3.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.model.Facility;
import it.uniroma3.model.Student;
import it.uniroma3.repository.FacilityRepository;

@Transactional
@Service
public class FacilityService {

	@Autowired
	private FacilityRepository facilityRepository; 
	
	public Facility save(Facility facility) {
		return this.facilityRepository.save(facility);
	}

	public List<Facility> findAll() {
		return (List<Facility>) this.facilityRepository.findAll();
	}
	
	public Facility findById(Long id) {
		Optional<Facility> facility = this.facilityRepository.findById(id);
		if (facility.isPresent()) 
			return facility.get();
		else
			return null;
	}
	
	public List<Facility> findByNameAndAddressAndEmail(String name, String address, String email){
		List<Facility> facilities = this.facilityRepository.findByNameAndAddressAndEmail(name, address, email);
		return facilities;
	}
	
	public boolean alreadyExists(Facility centro) {
		List<Facility> facilities = this.facilityRepository.findByNameAndAddressAndEmail(centro.getName(), centro.getAddress(), centro.getEmail());
		if (facilities.size() > 0)
			return true;
		else 
			return false;
	}
	
}



