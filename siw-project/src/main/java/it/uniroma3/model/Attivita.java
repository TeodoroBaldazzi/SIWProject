package it.uniroma3.model;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Entity
public class Attivita {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable=false)
	private String name;
	
	@Column(nullable=false)
	private Date dataOra;
	
	@ManyToMany
	private List<Allievo> allieviIscritti;
	
	public Attivita() {}

	public Attivita(String name, String surname, String city) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void addAllievo(Allievo a) {
		this.allieviIscritti.add(a);
	}
	
	public void removeAllievo(Allievo a) {
		this.allieviIscritti.remove(a);
	}
	public Date getDataOra() {
		return dataOra;
	}

	public void setDate(Date date) {
		this.dataOra = date;
	}
	
}

