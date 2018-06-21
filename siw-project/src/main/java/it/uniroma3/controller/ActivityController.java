package it.uniroma3.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import it.uniroma3.service.FacilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import it.uniroma3.controller.validator.ActivityValidator;
import it.uniroma3.model.Activity;
import it.uniroma3.model.Facility;
import it.uniroma3.model.Participation;
import it.uniroma3.model.Student;
import it.uniroma3.service.ActivityService;
import it.uniroma3.service.PartecipazioneService;
import it.uniroma3.service.StudentService;


@Controller
@SessionAttributes({"activity","activities","partecipazione","student"})
public class ActivityController {

	@Autowired
	private ActivityService activityService;


	@Autowired
	private FacilityService facilityService;

	@Autowired
	private PartecipazioneService partecipazioneService;
	

	@Autowired
	private ActivityValidator validator;


	@InitBinder("activity")
    public void initBinder(WebDataBinder binder) {
        //try {
    	CustomDateEditor editor = new CustomDateEditor(new SimpleDateFormat("dd-MM-yyyy HH:mm"), true);
        binder.registerCustomEditor(Date.class, editor);
        //}
        //catch(Exception e) {
        //	 throw new IllegalArgumentException("Wrong Format");
        //}
    }

	@RequestMapping("/activities")
	public String attivita(Model model, HttpSession session) {
		Facility current = (Facility)session.getAttribute("currentFacility");
		model.addAttribute("activities", current.getAttivitaSvolte());
		return "activitiesList";
	}

	@RequestMapping("/addActivity")
	public String addAttivita(Model model) {
		model.addAttribute("activity", new Activity());
		return "activityForm";
	}

	@RequestMapping(value = "/activity/{id}", method = RequestMethod.GET)
	public String getAttivita(@PathVariable("id") Long id, Model model) {
		model.addAttribute("activity", this.activityService.findById(id));
		return "showActivity";
	}

	@RequestMapping(value = "/activity", method = RequestMethod.POST)
	public String newAttivita(@Valid @ModelAttribute("activity") Activity attivita, Model model, BindingResult bindingResult, HttpSession session) {
		this.validator.validate(attivita, bindingResult);

		if (!bindingResult.hasErrors()) {
			Facility corrente = (Facility)session.getAttribute("currentFacility");

			corrente.addAttivita(attivita);

			this.activityService.save(attivita);

			this.facilityService.save(corrente);

			model.addAttribute("activities", this.activityService.findAll());
			return "activitiesList";
		}

		return "activityForm";
	}

	@RequestMapping(value="/chooseActivity/{id}", method = RequestMethod.GET)
	public String chooseActivity(@PathVariable("id") Long id, Model model, HttpSession session) {
		Activity activity = this.activityService.findById(id);
		model.addAttribute("activity",activity);
		if(activity!=null) {
			Student current = (Student) session.getAttribute("student");
			/*
			activity.addAllievo(current);
			current.addActivity(activity);
			activityService.save(activity);
			*/
			Participation partecipazione = new Participation(current, activity);

			if(!partecipazioneService.alreadyExists(partecipazione) && !(activity.getPartecipazioni().size()==activity.getLimitePartecipanti())) {
				//activity.addPartecipazione(partecipazione);
				//this.activityService.save(activity);
				
				model.addAttribute("student",current);
				model.addAttribute("partecipazione", partecipazione);
				return "savePartecipazione";
			}
			model.addAttribute("exists","Cannot perform operation");

		}
		return "activitiesList";
	}


	@RequestMapping(value = "/activity/{id}/participations", method = RequestMethod.GET)
	public String getPartecipazioni(@PathVariable("id") Long id, Model model) {
		Activity activity = (Activity) this.activityService.findById(id);
		model.addAttribute("participations", activity.getPartecipazioni());
		model.addAttribute("thisActivity",id);
		return "showParticipations";
	}


}


