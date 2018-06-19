package it.uniroma3.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import it.uniroma3.controller.validator.FacilityValidator;
import it.uniroma3.model.Activity;
import it.uniroma3.model.Facility;
import it.uniroma3.model.FacilityManager;
import it.uniroma3.service.FacilityService;

@Controller
@SessionAttributes({"facility","manager"})
public class FacilityController {

	@Autowired
	private FacilityService facilityService;

	@Autowired
	private FacilityValidator validator;

	@RequestMapping("/facilities")
	public String centri(Model model) {
		model.addAttribute("facilities", this.facilityService.findAll());
		return "facilitiesList";
	}

	@RequestMapping("/addFacility")
	public String addCentri(Model model) {
		model.addAttribute("facility", new Facility());
		return "facilityForm";
	}

	@RequestMapping(value = "/facility/{id}", method = RequestMethod.GET)
	public String getCentri(@PathVariable("id") Long id, Model model) {
		model.addAttribute("facility", this.facilityService.findById(id));
		return "showFacility";
	}

	@RequestMapping(value = "/facility", method = RequestMethod.POST)
	public String newCentri(@Valid @ModelAttribute("facility") Facility facility, Model model, BindingResult bindingResult) {		
		this.validator.validate(facility, bindingResult);

		if (this.facilityService.alreadyExists(facility)) {
			model.addAttribute("exists", "Facility already exists");
			model.addAttribute("esistente", this.facilityService.findByNameAndAddressAndEmail(facility.getName(),
					facility.getAddress(), facility.getEmail()));			
			return "facilityForm";
		}
		else
			if (!bindingResult.hasErrors()) {
				//this.facilityService.save(facility);
				//model.addAttribute("facilities", this.facilityService.findAll());
				//return "facilitiesList";
				model.addAttribute("facility",facility);
				model.addAttribute("manager",new FacilityManager());
				return "managerForm";
			}
		
		return "facilityForm";
	}

	@RequestMapping(value="/confirmFacility", method = RequestMethod.GET)
	public String confirmCentri(@ModelAttribute("facility") Facility facility, Model model) {

		this.facilityService.save(facility);
		model.addAttribute("facilities", this.facilityService.findAll());
		return "facilitiesList";

	}
	
	@RequestMapping(value = "/facility/{id}/activities", method = RequestMethod.GET)
	public String getAttivita(@PathVariable("id") Long id, Model model) {
		Facility facility = (Facility) this.facilityService.findById(id);
		model.addAttribute("facility",facility);
		//model.addAttribute("activities", facility.getAttivitaSvolte());
		return "chooseTemporalFilter";
	}
	
	@RequestMapping("/filterActivities")
	public String filterActivities(@RequestParam("inferior")String inferior, @RequestParam("inferior")String superior,
			Model model, HttpSession session) {
		Facility facility = (Facility) session.getAttribute("facility");
		Date inf = new Date(inferior);
		Date sup = new Date(superior);
		
		//NON SO SE CONVENGA COSI' OPPURE CON UNA QUERY PARTICOLARE
		Map<Long,Activity> tmp = facility.getAttivitaSvolte();
		Map<Long,Activity> activities = new HashMap<>();
		
		//METODI AFTER E BEFORE DI DATE UTILI
		for (Long id : tmp.keySet()) {
			Activity a = tmp.get(id);
			if(a.getDataOra().after(inf) && a.getDataOra().before(sup))
				activities.put(id, a);
		}

		
		model.addAttribute("activities", activities);
		return "showActivities";
		
	}

}

