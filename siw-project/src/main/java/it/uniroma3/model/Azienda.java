package it.uniroma3.model;

import java.util.List;

import javax.persistence.*;

@Entity
public class Azienda {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable=false)
	private String name;
	
	@ManyToMany(fetch=FetchType.LAZY)
	private List<Allievo> iscritti;

	public Azienda() {}

	public Azienda(String name, String surname, String city) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}


	public Long getId() {
		return id;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void addAllievo(Allievo a) {
		this.iscritti.add(a);
	}
	
	public Allievo getAllievoByCodice(Long codice) {
		for(Allievo a : iscritti)
			if(a.getId()==codice)
				return a;
		return null;
	}
}

