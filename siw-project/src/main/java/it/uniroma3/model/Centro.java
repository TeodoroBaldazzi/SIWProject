package it.uniroma3.model;

import java.util.List;

import javax.persistence.*;

@Entity
public class Centro {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable=false)
	private String name;

	@Column(nullable=false)
	private String email;
	
	private String telefono;
	
	@Column(nullable=false)
	private int capienza;
	
	@OneToMany
	@JoinColumn(name="centro_id")
	private List<Attivita> attivitaSvolte;

	@OneToMany(mappedBy="centroDiAppartenenza")
	private List<Responsabile> responsabili;

	
	public Centro() {}

	public Centro(String name, String surname, String city) {
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public int getCapienza() {
		return capienza;
	}

	public void setCapienza(int capienza) {
		this.capienza = capienza;
	}
	
	public Attivita getAttivitabyName(String nomeAttivita) {
		for(Attivita a : attivitaSvolte)
			if(a.getName().equals(nomeAttivita))
				return a;
		return null;
	}
	
	public void addAttivita(Attivita a) {
		this.attivitaSvolte.add(a);
	}
	
	public void removeAttivita(Attivita a) {
		this.attivitaSvolte.remove(a);
	}
	
	
}

