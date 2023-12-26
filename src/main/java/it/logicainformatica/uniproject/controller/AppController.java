package it.logicainformatica.uniproject.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.logicainformatica.uniproject.model.Corso;
import it.logicainformatica.uniproject.model.Studente;
import it.logicainformatica.uniproject.service.CorsoService;
import it.logicainformatica.uniproject.service.StudenteService;

@Controller
@RequestMapping("/segreteria")
public class AppController {
	
	@Autowired
	private StudenteService studenteService;
	
	@Autowired
	private CorsoService corsoService;
	
	// mapping per i corsi
	
	@GetMapping("/corsi/list")
	public String findAllCorsi(Model model) {
		List<Corso> list_corsi = corsoService.findAll();
		
		model.addAttribute("corsi", list_corsi);
		
		return "list-corsi";
	}
	
	@GetMapping("/corsi/form")
	public String formCorso(@RequestParam("corsoId") String id, Model model) {
		List<String> errori = new ArrayList<String>();
		Corso corso = corsoService.findById(id);
		
		if(corso != null) {
			model.addAttribute("corso", corso);
			model.addAttribute("mode", "update");
		}
		else {
			model.addAttribute("corso", new Corso());
			model.addAttribute("mode", "new");
		}
		
		model.addAttribute("errori", errori);
		
		return "corso-form";
	}
	
	@PostMapping("/corsi/save")
	public String saveCorso(@ModelAttribute("corso") Corso corso, @RequestParam("mode") String mode, Model model) {
		List<String> errori = new ArrayList<String>();
				
		if(corso.getId().trim() == "") errori.add("Il campo 'ID' è obbligatorio;");
		else {
			if(corso.getId().trim().contains(" ")) {
				errori.add("Il campo ID non può contenere spazi;");
			}
			else {
				if(mode.equals("new")) {
					Corso c = corsoService.findById(corso.getId().trim());
					if(c != null) errori.add("ID già presente nel sistema");
				}
			}
		}
		
		if(corso.getNome().trim() == "") errori.add("Il campo 'nome' è obbligatorio;");
		if(corso.getN_esami() < 9) errori.add("Numero di esami inferiori al minimo;");
			
		if(errori.isEmpty()) {
			corsoService.save(corso);
			
			return "redirect:/segreteria/corsi/list";
		}
		
		model.addAttribute("corso", corso);
		model.addAttribute("mode", mode);
		model.addAttribute("errori", errori);
		
		return "corso-form";
		
	}
	
	@GetMapping("/corsi/delete/{id}")
	public String deleteCorso(@PathVariable String id) {
		corsoService.delete(id);
		
		return "redirect:/segreteria/corsi/list";
	}
	
	// mapping per gli studenti
	
	@GetMapping("/studenti/list")
	public String findAllStudenti(Model model) {
		List<Studente> list_studenti = studenteService.findAll();
		List<Corso> list_corsi = corsoService.findAll();
		
		model.addAttribute("studenti", list_studenti);
		model.addAttribute("corsi", list_corsi);
		
		return "list-studenti";
	}
	
	@GetMapping("/studenti/form")
	public String formStudente(@RequestParam("studenteMatricola") String matricola, Model model) {
		
		if(corsoService.findAll().isEmpty()) return "redirect:/";
		
		List<String> errori = new ArrayList<String>();
		Studente studente = studenteService.findById(matricola);
		List<Corso> corsi = corsoService.findAll();
		
		if(studente != null) {
			model.addAttribute("studente", studente);
			model.addAttribute("mode", "update");
		}
		else {
			model.addAttribute("studente", new Studente());
			model.addAttribute("mode", "new");
		}
		
		model.addAttribute("corsi", corsi);
		model.addAttribute("errori", errori);
		
		return "studente-form";
	}
	
	@PostMapping("/studenti/save")
	public String saveStudente(@ModelAttribute("studente") Studente studente, @RequestParam("mode") String mode, @RequestParam("corso_id") String corso_id, Model model) {
		List<String> errori = new ArrayList<String>();
		List<Corso> corsi = corsoService.findAll();
				
		if(studente.getMatricola().trim() == "") errori.add("Il campo 'matricola' è obbligatorio;");
		else {
			if(studente.getMatricola().trim().contains(" ")) {
				errori.add("Il campo 'matricola' non può contenere spazi;");
			}
			else {
				if(mode.equals("new")) {
					Studente s = studenteService.findById(studente.getMatricola().trim());
					if(s != null) errori.add("La matricola è già presente nel sistema");
				}
			}
		}
		
		if(studente.getEmail().trim() == "") errori.add("Il campo 'email' è obbligatorio;");
		else {
			if(!studente.isEmailValid()) {
				errori.add("Il campo 'email' non è valido;");
			}
			else {
				if(mode.equals("new")) {
					Studente s = studenteService.findByEmail(studente.getEmail().trim());
					if(s != null) errori.add("La email è già presente nel sistema");
				}
			}
		}
		
		if(studente.getNome().trim() == "") errori.add("Il campo 'nome' è obbligatorio;");
		if(studente.getCognome().trim() == "") errori.add("Il campo 'cognome' è obbligatorio;");
		if(studente.getIndirizzo().trim() == "") errori.add("Il campo 'indirizzo' è obbligatorio;");
		if(studente.getCitta().trim() == "") errori.add("Il campo 'città' è obbligatorio;");
		if(studente.getData_nascita() == null) errori.add("Il campo 'data di nascita' è obbligatorio;");
		if(corso_id.trim() == "") errori.add("Il campo 'corso' è obbligatorio;");
			
		if(errori.isEmpty()) {
			Corso corso = new Corso();
			corso.setId(corso_id);
			studente.setCorso(corso);
			
			studenteService.save(studente);
			
			return "redirect:/segreteria/studenti/list";
		}
		
		model.addAttribute("studente", studente);
		model.addAttribute("corsi", corsi);
		model.addAttribute("mode", mode);
		model.addAttribute("errori", errori);
		
		return "studente-form";
		
	}
	
	@GetMapping("/studenti/delete/{id}")
	public String deleteStudente(@PathVariable String id) {
		studenteService.delete(id);
		
		return "redirect:/segreteria/studenti/list";
	}

}