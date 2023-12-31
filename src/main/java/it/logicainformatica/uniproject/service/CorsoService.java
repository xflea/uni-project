package it.logicainformatica.uniproject.service;

import java.util.List;

import it.logicainformatica.uniproject.model.Corso;
import it.logicainformatica.uniproject.model.Studente;

public interface CorsoService {
	
	public List<Corso> findAll();
	
	public List<Studente> findStudentsOfCourse(String id_corso);
	
	public void save(Corso corso);
	
	public Corso findById(String id);
	
	public void delete(String id);

}