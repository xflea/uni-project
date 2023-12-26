package it.logicainformatica.uniproject.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.logicainformatica.uniproject.model.Corso;
import it.logicainformatica.uniproject.model.Studente;
import it.logicainformatica.uniproject.repository.CorsoRepository;

@Service
public class CorsoServiceImpl implements CorsoService {
	
	@Autowired
	private CorsoRepository corsoRepository;
	
	@Autowired
	private StudenteService studenteService;

	@Override
	public List<Corso> findAll() {
		return corsoRepository.findAll();
	}
	
	@Override
	public List<Studente> findStudentsOfCourse(String id_corso) {
		List<Studente> lista_studenti_del_corso = new ArrayList<Studente>();
		
		List<Studente> studenti = studenteService.findAll();
		
		for(Studente s : studenti)
			if(s.getCorso().getId().equals(id_corso)) lista_studenti_del_corso.add(s);
		
		return lista_studenti_del_corso;
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
		
		if(opt.isPresent()) {
			List<Studente> lista_studenti_del_corso = this.findStudentsOfCourse(id);
			
			if(lista_studenti_del_corso.isEmpty() == false) {
				for(Studente s : lista_studenti_del_corso)
					studenteService.delete(s.getMatricola());
			}
			
			corsoRepository.deleteById(id);
		}
	}

}