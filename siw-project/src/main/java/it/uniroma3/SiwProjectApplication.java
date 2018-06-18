package it.uniroma3;


import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import it.uniroma3.model.Activity;
import it.uniroma3.model.Student;
import it.uniroma3.service.ActivityService;
import it.uniroma3.service.StudentService;

@SpringBootApplication
public class SiwProjectApplication {

	
	public static void main(String[] args) {
		SpringApplication.run(SiwProjectApplication.class, args);
	}
	
	/*
	@PostConstruct
	public void init() {
		Student student = new Student("Teodoro", "Baldazzi", "teodoro.baldazzi@gmail.com");
		studentService.save(student);
	}
	*/
	
}
