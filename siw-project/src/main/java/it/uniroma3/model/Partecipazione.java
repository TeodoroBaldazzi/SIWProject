package it.uniroma3.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Partecipazione {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@ManyToOne
	private Student allievo;
	
	@ManyToOne
	private Activity attivita;
	
	public Partecipazione() {
	
	}
	
	public Partecipazione(Student allievo, Activity attivita) {
		this.allievo = allievo;
		this.attivita = attivita;
	}

	public Student getAllievo() {
		return allievo;
	}

	public void setAllievo(Student allievo) {
		this.allievo = allievo;
	}

	public Activity getAttivita() {
		return attivita;
	}

	public void setAttivita(Activity attivita) {
		this.attivita = attivita;
	}

	public Long getId() {
		return id;
	}
	
	
}
