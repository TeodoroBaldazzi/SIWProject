package it.uniroma3.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.model.Student;

public interface StudentRepository extends CrudRepository<Student, Long> {

	List<Student> findByNameAndSurnameAndEmail(String name, String surname, String email);

}
