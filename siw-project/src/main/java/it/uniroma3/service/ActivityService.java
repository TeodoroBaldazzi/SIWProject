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
	private ActivityRepository activityRepository; 
	
	public Activity save(Activity attivita) {
		return this.activityRepository.save(attivita);
	}

	public List<Activity> findAll() {
		return (List<Activity>) this.activityRepository.findAll();
	}
	
	public Activity findById(Long id) {
		Optional<Activity> attivita = this.activityRepository.findById(id);
		if (attivita.isPresent()) 
			return attivita.get();
		else
			return null;
	}

	/*
	public List<Activity> findByFacility() {
		return (List<Activity>) this.activityRepository.findByFacility();
	}
	*/

	/*
	public boolean alreadyExists(Activity attivita) {
		List<Activity> activities = this.activityRepository.findByName(attivita.getName());
		if (activities.size() > 0)
			return true;
		else 
			return false;
	}
	*/
}
