package it.uniroma3.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import it.uniroma3.model.Partecipazione;
import it.uniroma3.model.Student;
import it.uniroma3.service.PartecipazioneService;
import it.uniroma3.service.StudentService;

@Controller
public class PartecipazioneController {

	@Autowired
	private PartecipazioneService partecipazioneService;
	
	@Autowired
	private StudentService studentService;
	
	
	@RequestMapping("/salvaPartecipazione")
	public String savePartecipazione(Model model, HttpSession session) {
		Partecipazione partecipazione = (Partecipazione) session.getAttribute("partecipazione");
		Student current = (Student) session.getAttribute("student");
		
		current = studentService.findById(current.getId());
		current.addPartecipazione(partecipazione);
		this.studentService.save(current);
		
		this.partecipazioneService.save(partecipazione);
		return "activitiesList";
	}
}
