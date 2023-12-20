package it.logicainformatica.uniproject.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.logicainformatica.uniproject.model.Corso;
import it.logicainformatica.uniproject.repository.CorsoRepository;

@Service
public class CorsoServiceImpl implements CorsoService {
	
	@Autowired
	private CorsoRepository corsoRepository;

	@Override
	public List<Corso> findAll() {
		return corsoRepository.findAll();
	}

	@Override
	public void save(Corso corso) {
		corsoRepository.save(corso);
	}

	@Override
	public Corso findById(String id) {
		Optional<Corso> opt = corsoRepository.findById(id);
		
		if(opt.isPresent()) return opt.get();
		
		return null;
	}

	@Override
	public void delete(String id) {
		Optional<Corso> opt = corsoRepository.findById(id);
		
		if(opt.isPresent()) corsoRepository.deleteById(id);
	}

}