package it.logicainformatica.uniproject.service;

import java.util.List;

import it.logicainformatica.uniproject.model.Studente;

public interface StudenteService {
	
	public List<Studente> findAll();
	
	public void save(Studente studente);
	
	public Studente findById(String id);
	
	public Studente findByEmail(String email);
	
	public void delete(String id);

}