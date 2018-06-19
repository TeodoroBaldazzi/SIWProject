package it.uniroma3.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import it.uniroma3.controller.validator.ManagerValidator;
import it.uniroma3.model.Facility;
import it.uniroma3.model.FacilityManager;
import it.uniroma3.service.FacilityService;
import it.uniroma3.service.ManagerService;


@Controller
public class ManagerController {

	@Autowired
	private ManagerService managerService;

	@Autowired
	private FacilityService facilityService;

	@Autowired
	private ManagerValidator validator;

	@RequestMapping(value = "/manager", method = RequestMethod.POST)
	public String neManager(@Valid @ModelAttribute("manager") FacilityManager manager, 
			Model model, BindingResult bindingResult, HttpSession session) {		
		this.validator.validate(manager, bindingResult);

		if (this.managerService.alreadyExists(manager)) {
			model.addAttribute("exists", "Manager already in charge of a facility");
			Facility esistente = this.managerService.findById(manager.getId()).getCentroDiAppartenenza();
			model.addAttribute("esistente", esistente);
			return "managerForm";
		}
		else
			if (!bindingResult.hasErrors()) {
				Facility facility = (Facility) session.getAttribute("facility");
				facility.setResponsabile(manager);
				manager.setCentroDiAppartenenza(facility);
				
				this.facilityService.save(facility);
				this.managerService.save(manager);
				
				model.addAttribute("facilities", this.facilityService.findAll());
				return "facilitiesList";
			}

		return "managerForm";
	}

}
