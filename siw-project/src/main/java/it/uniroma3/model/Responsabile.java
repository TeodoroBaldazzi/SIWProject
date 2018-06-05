package it.uniroma3.model;

import javax.persistence.*;

@Entity
public class Responsabile {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable=false)
	private String name;

	@Column(nullable=false)
	private String surname;
	

	public Responsabile() {}

	public Responsabile(String name, String surname, String city) {
		this.name = name;
		this.surname = surname;
	}

	public String getName() {
		return this.name;
	}

	public String getSurname() {
		return this.surname;
	}

	public Long getId() {
		return id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	
}

