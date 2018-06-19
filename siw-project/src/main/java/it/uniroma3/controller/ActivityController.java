package it.uniroma3.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

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
import org.springframework.web.bind.annotation.SessionAttributes;

import it.uniroma3.controller.validator.ActivityValidator;
import it.uniroma3.model.Activity;
import it.uniroma3.model.Partecipazione;
import it.uniroma3.model.Student;
import it.uniroma3.service.ActivityService;
import it.uniroma3.service.PartecipazioneService;


@Controller
@SessionAttributes({"activity","activities","partecipazione"})
public class ActivityController {

	@Autowired
	private ActivityService activityService;

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
	public String attivita(Model model) {
		//model.addAttribute("activities", this.activityService.findByFacility());
		model.addAttribute("activities", this.activityService.findAll());
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
	public String newAttivita(@Valid @ModelAttribute("activity") Activity attivita, Model model, BindingResult bindingResult) {
		this.validator.validate(attivita, bindingResult);

		if (!bindingResult.hasErrors()) {
			this.activityService.save(attivita);
			model.addAttribute("activities", this.activityService.findAll());
			return "activitiesList";
		}

		return "activityForm";
	}

	@RequestMapping(value="/chooseActivity/{id}", method=RequestMethod.GET)
	public String chooseActivity(@PathVariable("id") Long id, Model model, HttpSession session) {
		Activity activity = this.activityService.findById(id);
		if(activity!=null) {
			Student current = (Student) session.getAttribute("student");
			/*
			activity.addAllievo(current);
			current.addActivity(activity);
			activityService.save(activity);
			*/
			Partecipazione partecipazione = new Partecipazione(current, activity);

			if(!partecipazioneService.alreadyExists(partecipazione)) {
				current.addPartecipazione(partecipazione);
				activity.addPartecipazione(partecipazione);
				model.addAttribute("partecipazione", partecipazione);
				return "savePartecipazione";
			}
			model.addAttribute("exists","true");

		}
		return "activitiesList";
	}

	//DA GESTIRE CASO ALLIEVO GIA' PRESENTE


}


