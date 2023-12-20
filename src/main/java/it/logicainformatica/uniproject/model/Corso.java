package it.logicainformatica.uniproject.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "corsi")
public class Corso {
	
	@Id
	@Column
	private String id;
	
	@Column(nullable = false)
	private String nome;
	
	@Column(nullable = true)
	private String indirizzo;
	
	@Column(nullable = false)
	private int n_esami;
	
	@OneToMany(mappedBy = "corso")
	private List<Studente> studenti;
	
	public Corso() {
		super();
	}

	public Corso(String id, String nome, String indirizzo, int n_esami, List<Studente> studenti) {
		super();
		this.id = id;
		this.nome = nome;
		this.indirizzo = indirizzo;
		this.n_esami = n_esami;
		this.studenti = studenti;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getIndirizzo() {
		return indirizzo;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	public int getN_esami() {
		return n_esami;
	}

	public void setN_esami(int n_esami) {
		this.n_esami = n_esami;
	}

	public List<Studente> getStudenti() {
		return studenti;
	}

	public void setStudenti(List<Studente> studenti) {
		this.studenti = studenti;
	}

}
