package it.uniroma3.model;

import javax.persistence.*;

@Entity
public class FacilityManager {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable=false)
	private String name;

	@Column(nullable=false)
	private String surname;
	
	@Column(nullable=false)
	private String username;
	
	@ManyToOne
    @JoinColumn(name = "centro_id")
	private Facility centroDiAppartenenza;

	public FacilityManager() {}

	public FacilityManager(String name, String surname, String username) {
		this.name = name;
		this.surname = surname;
		this.username = username;
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Facility getCentroDiAppartenenza() {
		return centroDiAppartenenza;
	}

	public void setCentroDiAppartenenza(Facility centroDiAppartenenza) {
		this.centroDiAppartenenza = centroDiAppartenenza;
	}
	
	
	
}

