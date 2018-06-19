package it.uniroma3.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.model.FacilityManager;
import it.uniroma3.repository.ManagerRepository;

@Transactional
@Service
public class ManagerService {

	@Autowired
	private ManagerRepository managerRepository; 
	
	public FacilityManager save(FacilityManager facility) {
		return this.managerRepository.save(facility);
	}

	public List<FacilityManager> findAll() {
		return (List<FacilityManager>) this.managerRepository.findAll();
	}
	
	public FacilityManager findById(Long id) {
		Optional<FacilityManager> manager = this.managerRepository.findById(id);
		if (manager.isPresent()) 
			return manager.get();
		else
			return null;
	}
	
	
	public boolean alreadyExists(FacilityManager responsabile) {
		List<FacilityManager> facilities = this.managerRepository.findByEmail(responsabile.getUsername());
		if (facilities.size() > 0)
			return true;
		else 
			return false;
	}
	
}


