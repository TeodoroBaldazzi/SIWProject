package it.uniroma3.model;

import javax.persistence.*;

@Entity
public class Centro {

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
	
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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

	public Integer getMaxCapacity() {
		return maxCapacity;
	}

	public void setMaxCapacity(Integer maxCapacity) {
		this.maxCapacity = maxCapacity;
	}

	
}

