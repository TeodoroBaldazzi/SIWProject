package it.uniroma3.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
	
	@Column
	private String phone;
	
	@Column
	private String birtdate;				//LocalDate
	
	@Column
	private String birthplace;
	

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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getBirtdate() {
		return birtdate;
	}

	public void setBirtdate(String birtdate) {
		this.birtdate = birtdate;
	}

	public String getBirthplace() {
		return birthplace;
	}

	public void setBirthplace(String birthplace) {
		this.birthplace = birthplace;
	}
	
	
}
