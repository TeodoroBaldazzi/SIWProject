package it.uniroma3.model;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Entity
public class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable=false)
	private String name;

	@Column(nullable=false)
	private String surname;
	
	@Column(nullable=false)
	private String email;
	
	private String telefono;
	
	private String luogoNascita;
	
	@Temporal(TemporalType.DATE)
	private Date dataNascita;
	
	@ManyToMany(fetch=FetchType.EAGER)
	private List<Attivita> attivita; 
	
	public Student() {}

	public Student(String name, String surname, String city) {
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

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setSurname(String surname) {
		this.surname = surname;
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

	public String getLuogoNascita() {
		return luogoNascita;
	}

	public void setLuogoNascita(String luogoNascita) {
		this.luogoNascita = luogoNascita;
	}

	public Date getDataNascita() {
		return dataNascita;
	}

	public void setDataNascita(Date dataNascita) {
		this.dataNascita = dataNascita;
	}

	public Attivita getAttivitabyName(String nomeAttivita) {
		for(Attivita a : attivita)
			if(a.getName().equals(nomeAttivita))
				return a;
		return null;
	}
	
	public void addAttivita(Attivita a) {
		this.attivita.add(a);
	}
	
	public void removeAttivita(Attivita a) {
		this.attivita.remove(a);
	}
	
}
