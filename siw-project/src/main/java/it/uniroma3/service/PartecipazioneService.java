package it.uniroma3.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.model.Participation;
import it.uniroma3.repository.PartecipazioneRepository;

@Transactional
@Service
public class PartecipazioneService {

	
	@Autowired
	private PartecipazioneRepository partecipazioneRepository;
	
	public Participation save(Participation partecipazione) {
		return this.partecipazioneRepository.save(partecipazione);
	}

	public boolean alreadyExists(Participation partecipazione) {
		List<Participation> partecipazioni = this.partecipazioneRepository.findByAllievoAndAttivita(partecipazione.getAllievo(),partecipazione.getAttivita());
		if(!partecipazioni.isEmpty())
			return true;
		return false;
	}
}
