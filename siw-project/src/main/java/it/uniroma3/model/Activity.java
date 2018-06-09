package it.uniroma3.model;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.*;

@Entity
public class Activity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable=false)
	private String name;
	
	@Column(nullable=false)
	private Date dataOra;
	
	@ManyToMany
	private Map<Long,Student> allieviIscritti;
	
	public Activity() {}

	public Activity(String name, Date dataOra) {
		this.name = name;
		this.dataOra = dataOra;
		this.allieviIscritti = new HashMap<>();
	}

		
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDataOra() {
		return dataOra;
	}

	public void setDataOra(Date dataOra) {
		this.dataOra = dataOra;
	}

	public Map<Long, Student> getAllieviIscritti() {
		return allieviIscritti;
	}

	public Long getId() {
		return id;
	}

	public void addAllievo(Student a) {
		this.allieviIscritti.put(a.getId(),a);
	}
	
	public void removeAllievo(Student a) {
		this.allieviIscritti.remove(a.getId());
	}

}
