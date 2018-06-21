package it.uniroma3.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import it.uniroma3.model.Activity;
import it.uniroma3.model.Facility;
import it.uniroma3.model.Participation;
import it.uniroma3.model.Student;
import it.uniroma3.service.ActivityService;
import it.uniroma3.service.PartecipazioneService;
import it.uniroma3.service.StudentService;

@Controller
public class PartecipazioneController {

	@Autowired
	private ActivityService activityService;
	
	@Autowired
	private PartecipazioneService partecipazioneService;
	
	@Autowired
	private StudentService studentService;
	
	
	@RequestMapping("/salvaPartecipazione")
	public String savePartecipazione(Model model, HttpSession session) {
		Participation partecipazione = (Participation) session.getAttribute("partecipazione");
		Student current = (Student) session.getAttribute("searchedStudent");
		Activity activity = (Activity) session.getAttribute("activity");
		
		//current = studentService.findById(current.getId());
		current.addPartecipazione(partecipazione);
		activity.addPartecipazione(partecipazione);

		this.partecipazioneService.save(partecipazione);
		this.studentService.save(current);
		this.activityService.save(activity);
		
		Facility corrente = (Facility)session.getAttribute("currentFacility");
		model.addAttribute("activities", corrente.getAttivitaSvolte());
		
		return "activitiesList";
	}
}
