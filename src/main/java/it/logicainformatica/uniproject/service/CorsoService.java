package it.logicainformatica.uniproject.service;

import java.util.List;

import it.logicainformatica.uniproject.model.Corso;

public interface CorsoService {
	
	public List<Corso> findAll();
	
	public void save(Corso corso);
	
	public Corso findById(String id);
	
	public void delete(String id);

}