package it.uniroma3.service;

import it.uniroma3.model.Role;
import it.uniroma3.model.Student;
import it.uniroma3.repository.RoleRepository;
import it.uniroma3.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class RoleService {

	@Autowired
	private RoleRepository roleRepository;
	
	public Role save(Role role) {
		return this.roleRepository.save(role);
	}

	public List<Role> findAll() {
		return (List<Role>) this.roleRepository.findAll();
	}
		
}

