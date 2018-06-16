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
	
	private String phone;
	
	private String birthplace;
	
	/*
	@Temporal(TemporalType.DATE)
	private Date birthdate;
	*/
	
	@Temporal(TemporalType.DATE)
	private Date dateTimeRegistration;
	
	@ManyToMany(fetch=FetchType.EAGER)
	private List<Activity> activities; 
	
	public Student() {}

	public Student(String name, String surname, String email) {
		this.name = name;
		this.surname = surname;
		this.email = email;
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

	/*
	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date dataNascita) {
		this.birthdate = dataNascita;
	}
	*/
	
	public Date getDateTimeRegistration() {
		return dateTimeRegistration;
	}

	public void setDateTimeRegistration(Date dateTimeRegistration) {
		this.dateTimeRegistration = dateTimeRegistration;
	}

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
	
	

}
