package it.uniroma3.controller;

import java.util.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import it.uniroma3.model.User;
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
@SessionAttributes({"userManager","facilities","facility"})
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
	public String newCentri(@Valid @ModelAttribute("facility") Facility facility, Model model,HttpSession session, BindingResult bindingResult) {
		this.validator.validate(facility, bindingResult);

		if (this.facilityService.alreadyExists(facility)) {
			model.addAttribute("exists", "Facility already exists");
			model.addAttribute("esistente", this.facilityService.findByNameAndAddressAndEmail(facility.getName(),
					facility.getAddress(), facility.getEmail()));

			model.addAttribute("facility",new Facility());
			return "facilityForm";
		}
		else
			if (!bindingResult.hasErrors()) {
				//this.facilityService.save(facility);
				//model.addAttribute("facilities", this.facilityService.findAll());
				//return "facilitiesList";
				model.addAttribute("facility",facility);
				//session.setAttribute("facility", facility);
				model.addAttribute("userManager", new User());
				return "managerForm";
			}

		model.addAttribute("facility",new Facility());
		return "facilityForm";
	}

	@RequestMapping(value="/confirmFacility", method = RequestMethod.GET)
	public String confirmCentri(@ModelAttribute("facility") Facility facility, Model model) {

		this.facilityService.save(facility);
		model.addAttribute("facilities", this.facilityService.findAll());
		return "facilitiesList";

	}
	
	@RequestMapping(value = "/facility/activities/{id}", method = RequestMethod.GET)
	public String getAttivita(@PathVariable("id") Long id, Model model, HttpSession session) {
		Facility facility = this.facilityService.findById(id);

		Date sup = new Date();

		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, -1);
		Date inf = cal.getTime();

		List<Activity> tmp = facility.getAttivitaSvolte();
		List<Activity> activities = new ArrayList<>();

		for (Activity a : tmp) {
			if(a.getDataOra().after(inf) && a.getDataOra().before(sup))
				activities.add(a);
		}


		model.addAttribute("activities", activities);
		model.addAttribute("thisFacility",id);
		return "showActivities";
	}
	
	/*@SuppressWarnings("deprecation")
	@RequestMapping("/filterActivities")
	public String filterActivities(Model model, HttpSession session) {
		Facility facility = (Facility) session.getAttribute("facility");
		Date inf = new Date();

		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, -1);
		Date sup = cal.getTime();
		
		Map<Long,Activity> tmp = facility.getAttivitaSvolte();
		Map<Long,Activity> activities = new HashMap<>();
		
		for (Long id : tmp.keySet()) {
			Activity a = tmp.get(id);
			if(a.getDataOra().after(inf) && a.getDataOra().before(sup))
				activities.put(id, a);
		}

		
		model.addAttribute("activities", activities);
		return "showActivities";
		
	}*/

}

