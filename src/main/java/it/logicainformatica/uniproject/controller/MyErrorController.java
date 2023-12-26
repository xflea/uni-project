package it.logicainformatica.uniproject.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MyErrorController implements ErrorController {

	@RequestMapping("/error")
	public String handleError(HttpServletRequest request, Model model) {
		Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
		String testo_errore = "";
		int codice_errore = 0;
		
		if(status != null) {
			codice_errore = Integer.valueOf(status.toString());
			
			switch (codice_errore) {
				case 400:
					testo_errore = "Richiesta invalida";
					break;
				
				case 403:
					testo_errore = "Risorsa protetta";
					break;
				
				case 404:
					testo_errore = "Risorsa non trovata";
					break;
					
				case 500:
					testo_errore = "Errore del server";
					break;
	
				default:
					testo_errore = "Qualcosa è andato storto...";
					break;
			}
		}
		
		model.addAttribute("testo_errore", testo_errore);
		model.addAttribute("codice_errore", codice_errore);
		
		return "error";
	}

	@Deprecated
	public String getErrorPath() {
		return null;
	}

}