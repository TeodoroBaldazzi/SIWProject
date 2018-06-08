package it.uniroma3.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.model.Student;
import it.uniroma3.repository.StudentRepository;

@Transactional
@Service
public class StudentService {

	@Autowired
	private StudentRepository allievoRepository; 
	
	public Student save(Student allievo) {
		return this.allievoRepository.save(allievo);
	}

	public List<Student> findAll() {
		return (List<Student>) this.allievoRepository.findAll();
	}
	
	public Student findById(Long id) {
		Optional<Student> allievo = this.allievoRepository.findById(id);
		if (allievo.isPresent()) 
			return allievo.get();
		else
			return null;
	}

	public boolean alreadyExists(Student allievo) {
		List<Student> customers = this.allievoRepository.findByNameAndSurnameAndEmail(allievo.getName(), allievo.getSurname(), allievo.getEmail());
		if (customers.size() > 0)
			return true;
		else 
			return false;
	}	
}

