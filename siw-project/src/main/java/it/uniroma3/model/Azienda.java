package it.uniroma3.model;

import javax.persistence.*;

@Entity
public class Azienda {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable=false)
	private String name;
	

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
	
	
}

