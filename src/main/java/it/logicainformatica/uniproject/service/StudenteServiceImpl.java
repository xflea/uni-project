package it.logicainformatica.uniproject.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.logicainformatica.uniproject.model.Studente;
import it.logicainformatica.uniproject.repository.StudenteRepository;

@Service
public class StudenteServiceImpl implements StudenteService {
	
	@Autowired
	private StudenteRepository studenteRepository;

	@Override
	public List<Studente> findAll() {
		return studenteRepository.findAll();
	}

	@Override
	public void save(Studente studente) {
		studenteRepository.save(studente);
	}

	@Override
	public Studente findById(String id) {
		Optional<Studente> opt = studenteRepository.findById(id);
		
		if(opt.isPresent()) return opt.get();
		
		return null;
	}

	@Override
	public void delete(String id) {
		Optional<Studente> opt = studenteRepository.findById(id);
		
		if(opt.isPresent()) studenteRepository.deleteById(id);
	}

}
