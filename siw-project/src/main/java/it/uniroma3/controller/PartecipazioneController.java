package it.uniroma3.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import it.uniroma3.model.Partecipazione;
import it.uniroma3.service.PartecipazioneService;

@Controller
public class PartecipazioneController {

	@Autowired
	private PartecipazioneService partecipazioneService;
	
	@RequestMapping("/salvaPartecipazione")
	public String savePartecipazione(Model model, HttpSession session) {
		this.partecipazioneService.save((Partecipazione) session.getAttribute("partecipazione"));
		return "activitiesList";
	}
}
