package it.uniroma3.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.uniroma3.controller.validator.ActivityValidator;
import it.uniroma3.model.Activity;
import it.uniroma3.service.ActivityService;


@Controller
public class ActivityController {

	@Autowired
	private ActivityService activityService;

	@Autowired
	private ActivityValidator validator;

	@RequestMapping("/activities")
	public String attivita(Model model) {
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

}


