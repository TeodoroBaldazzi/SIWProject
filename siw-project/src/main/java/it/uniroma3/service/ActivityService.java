package it.uniroma3.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.model.Activity;
import it.uniroma3.repository.ActivityRepository;


@Transactional
@Service
public class ActivityService {

	
	@Autowired
	private ActivityRepository attivitaRepository; 
	
	public Activity save(Activity allievo) {
		return this.attivitaRepository.save(allievo);
	}

	public List<Activity> findAll() {
		return (List<Activity>) this.attivitaRepository.findAll();
	}
	
	public Activity findById(Long id) {
		Optional<Activity> allievo = this.attivitaRepository.findById(id);
		if (allievo.isPresent()) 
			return allievo.get();
		else
			return null;
	}


}
