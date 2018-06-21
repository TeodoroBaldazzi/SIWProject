package it.uniroma3.model;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.*;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Activity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable=false)
	private String name;
	
	@Column(nullable=false)
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(style="dd/MM/yyyy HH:mm")
	private Date dataOra;

	@Column(nullable = false)
	private int limitePartecipanti;

	/*
	@ManyToMany
	private Map<Long,Student> allieviIscritti;
	*/

	@OneToMany
	private Map<Long,Participation> partecipazioni;
	
	
	public Activity() {}

	public Activity(String name, Date dataOra, int limitePartecipanti) {
		this.name = name;
		this.dataOra = dataOra;
		this.limitePartecipanti = limitePartecipanti;
		//this.allieviIscritti = new HashMap<>();
		
	}

		
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getLimitePartecipanti() {
		return this.limitePartecipanti;
	}

	public void setLimitePartecipanti(int limite) {
		this.limitePartecipanti = limite;
	}

	public Date getDataOra() {
		return dataOra;
	}

	public void setDataOra(Date dataOra) {
		this.dataOra = dataOra;
	}

	/*
	public Map<Long, Student> getAllieviIscritti() {
		return allieviIscritti;
	}
	 */
	public Long getId() {
		return id;
	}

	/*
	public void addAllievo(Student a) {
		this.allieviIscritti.put(a.getId(),a);
	}
	
	public void removeAllievo(Student a) {
		this.allieviIscritti.remove(a.getId());
	}
	*/
	
	public void addPartecipazione(Participation a) {
		this.partecipazioni.put(a.getId(),a);
	}
	
	public void removePartecipazione(Participation a) {
		this.partecipazioni.remove(a.getId());
	}
	
	public Map<Long, Participation> getPartecipazioni(){
		return this.partecipazioni;
	}
	

}

