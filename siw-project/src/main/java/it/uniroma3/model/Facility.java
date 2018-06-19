package it.uniroma3.model;

import java.util.List;

import javax.persistence.*;

@Entity
public class Facility {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(nullable=false)
	private String name;

	@Column(nullable=false)
	private String address;

	@Column(nullable=false)
	private String email;

	@Column(nullable=false)
	private String phone;

	@Column(nullable=false)
	private Integer maxCapacity;

	@OneToMany
	@JoinColumn(name="centro_id")
	private List<Activity> attivitaSvolte;

	/*
	@OneToMany(mappedBy="centroDiAppartenenza")
	private List<Responsabile> responsabili;
	*/
	@OneToOne(mappedBy="centroDiAppartenenza",cascade=CascadeType.ALL)
	private FacilityManager responsabile;
	
	public Facility() {}

	public Facility(String name, String surname, String city) {
		this.name = name;
	}


	public Long getId() {
		return id;
	}
	
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Activity getAttivitabyName(String nomeAttivita) {
		for(Activity a : attivitaSvolte)
			if(a.getName().equals(nomeAttivita))
				return a;
		return null;
	}

	public void addAttivita(Activity a) {
		this.attivitaSvolte.add(a);
	}

	public void removeAttivita(Activity a) {
		this.attivitaSvolte.remove(a);
	}


	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Integer getMaxCapacity() {
		return maxCapacity;
	}

	public void setMaxCapacity(Integer maxCapacity) {
		this.maxCapacity = maxCapacity;
	}

	public FacilityManager getResponsabile() {
		return responsabile;
	}

	public void setResponsabile(FacilityManager responsabile) {
		this.responsabile = responsabile;
	}
	
	
}

