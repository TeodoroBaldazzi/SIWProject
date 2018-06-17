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

import it.uniroma3.controller.validator.FacilityValidator;
import it.uniroma3.model.Facility;
import it.uniroma3.service.FacilityService;

@Controller
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
				this.facilityService.save(facility);
				model.addAttribute("facilities", this.facilityService.findAll());
				return "facilitiesList";
			}

		return "facilityForm";
	}

	@RequestMapping(value="/confirmFacility", method = RequestMethod.GET)
	public String confirmCentri(@ModelAttribute("facility") Facility facility, Model model) {

		this.facilityService.save(facility);
		model.addAttribute("facilities", this.facilityService.findAll());
		return "facilitiesList";

	}

}

