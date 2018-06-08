package it.uniroma3.model;

import java.util.List;

import javax.persistence.*;

@Entity
public class Azienda {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable=false)
	private String name;
	
	@ManyToMany(fetch=FetchType.LAZY)
	private List<Student> iscritti;

	public Azienda() {}

	public Azienda(String name, String surname, String city) {
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
	
	public void addAllievo(Student a) {
		this.iscritti.add(a);
	}
	
	public Student getAllievoByCodice(Long codice) {
		for(Student a : iscritti)
			if(a.getId()==codice)
				return a;
		return null;
	}
}

