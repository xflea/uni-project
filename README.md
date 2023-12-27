
# Progetto Spring: Segreteria Universitaria

Progetto per il corso Spring di LogicaInformatica.


## Installazione e setup

Per l'installazione è solamente necessario clonare il progetto

```bash
  git clone https://github.com/xflea/uni-project.git
```
Per un corretto funzionamento, assicucarsi di mettere le corrette credenziali per il proprio ambiente di sviluppo nel file `src/main/resources/application.properties`
## Considerazioni sugli studenti

È possibile aggiungere, modificare ed eliminare gli studenti dal sistema.

Non è possibile aggiungere studenti se non ci sono corsi nel sistema.

Durante la creazione di uno studente, oltre ai controlli sui campi obbligatori, vengono effettuati controlli di correttezza e unicità sia sulla matricola sia sulla email, questo perché a livello funzionale dovrebbe essere possibile, ad esempio, che 2 email identiche siano presenti nel sistema.

NB: il testo del compito specificava che il campo "matricola" e "corso" non potessero essere modificati.


## Considerazioni sui corsi

È possibile aggiungere, modificare ed eliminare i corsi dal sistema.

Dato che nel testo veniva specificato che il campo "corso" dello studente non può essere nullo, se si cancellasse un corso, gli studenti resterebbero con un campo nullo nel sistema.
Per ovviare a questa problematica, ho pensato che se si cancella un corso, allora verranno eliminati nel sistema anche tutti gli studenti appartenenti a quel corso (questo perché, a livello funzionale, non ha senso avere nel sistema degli studenti che non sono assegnati a nessun corso).
Se si effettua tale operazione, si verrà portati a una pagina dove si chiede la conferma dell'azione che si vuole effettuare.

NB: il testo del compito specificava che il campo "id" non potesse essere modificato.

## Gestione customizzata degli errori

Eventuali errori vengono smistati e gestiti da un controller custom che sovrascrive quello già implementato da Spring, questo è stato fatto per pure esercizio personale.
Questo controller, non fa altro che individuare che tipo di errore si è verificato, e di reindirizzare l'utente su una pagina customizzata dove gli viene comunicato il codice dell'errore. Il codice del controller è visibile sul file: `src/main/java/it/logicainformatica/uniproject/controller/MyErrorController.java`
## Stack utilizzato

- Java Spring;
- Thymeleaf;
- Maven (gestore delle dipendenze);
- Bootstrap 5 (per il frontend, "installato" tramite CDN e non come dipendenza);
