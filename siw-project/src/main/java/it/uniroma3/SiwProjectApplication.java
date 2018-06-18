package it.uniroma3;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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

	@Autowired
	private StudentService studentService;

	@Autowired
	private ActivityService activityService;

	public static void main(String[] args) {
		SpringApplication.run(SiwProjectApplication.class, args);
	}

	@PostConstruct
	public void init() {
		Student student = new Student("Teodoro", "Baldazzi", "teodoro.baldazzi@gmail.com");
		studentService.save(student);
		Activity activity = new Activity("Prova", parseDate("10-06-2018"), 10);
		activityService.save(activity);
	}

	public static Date parseDate(String date) {
		try {
			return new SimpleDateFormat("dd-MM-yyyy").parse(date);
		} catch (ParseException e) {
			return null;
		}
	}
}
