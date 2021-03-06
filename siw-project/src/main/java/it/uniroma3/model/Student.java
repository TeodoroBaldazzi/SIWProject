package it.uniroma3.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.*;

import org.hibernate.annotations.GeneratorType;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Student {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq")
	@SequenceGenerator(name="seq", sequenceName="student_sequence", initialValue = 2564, allocationSize=1)
	private Long id;
	
	@Column(nullable=false)
	private String name;

	@Column(nullable=false)
	private String surname;
	
	@Column(nullable=false)
	private String email;
	
	private String phone;
	
	private String birthplace;
	
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(style="dd/MM/yyyy")
	private Date birthdate;
	
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(style="dd/MM/yyyy HH:mm:ss")
	private Date dateTimeRegistration;
	
	/*
	@ManyToMany(fetch=FetchType.EAGER)
	private List<Activity> activities; 
	*/
	
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Participation> partecipazioni;
	
	
	public Student() {}

	public Student(String name, String surname, String email) {
		this.name = name;
		this.surname = surname;
		this.email = email;
		partecipazioni = new ArrayList<>();
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
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String telefono) {
		this.phone = telefono;
	}

	public String getBirthplace() {
		return birthplace;
	}

	public void setBirthplace(String luogoNascita) {
		this.birthplace = luogoNascita;
	}

	
	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date dataNascita) {
		this.birthdate = dataNascita;
	}
	
	
	public Date getDateTimeRegistration() {
		return dateTimeRegistration;
	}

	public void setDateTimeRegistration(Date dateTimeRegistration) {
		this.dateTimeRegistration = dateTimeRegistration;
	}

	/*
	public Activity getActivityByName(String nomeAttivita) {
		for(Activity a : activities)
			if(a.getName().equals(nomeAttivita))
				return a;
		return null;
	}
	
	public void addActivity(Activity a) {
		this.activities.add(a);
	}
	
	public void removeActivity(Activity a) {
		this.activities.remove(a);
	}
	*/
	
	public void addPartecipazione(Participation a) {
		this.partecipazioni.add(a);
	}
	
	public void removePartecipazione(Participation a) {
		this.partecipazioni.remove(a);
	}
	

}
