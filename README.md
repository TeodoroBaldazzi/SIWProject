# SIWProject

Casi d’uso

Caso UC1: Iscrizione nuovo Allievo 

Attore primario: Responsabile del centro

Scenario principale di successo: 
1.	Un Allievo si reca dal Responsabile del Centro per effettuare l’iscrizione.
2.	Il Responsabile inizia la registrazione di un nuovo Allievo.
3.	Il Responsabile chiede al nuovo Allievo i suoi dati (nome, cognome, data e luogo di nascita, telefono e email).
4.	L’Allievo fornisce i dati richiesti.
5.	Il Responsabile inserisce nel sistema i dati relativi all’Allievo.
6.	Il Sistema salva i dati in modo opportuno, insieme a data e ora di registrazione.
7.	Il Responsabile consegna all’Allievo un codice che lo identifica (tessera).
Estensioni:
6a. Allievo già registrato precedentemente:
1.	Il Sistema recupera le informazioni associate all’Allievo
2.	Il Sistema mostra tali informazioni


Caso UC2: Iscrizione a una o più Attività

Attore primario: Responsabile del centro

Scenario principale di successo: 
1.	Un Allievo si reca dal Responsabile del Centro per iscriversi a un’Attività.
2.	Il Responsabile chiede all’Allievo il suo codice identificativo (tessera).
3.	Il Responsabile inizia l’iscrizione a un’Attività.
4.	Il Responsabile, quando richiesto dal Sistema, inserisce il codice dell’Allievo.
5.	Il Sistema mostra un elenco delle Attività disponibili per quel Centro.
6.	L’Allievo specifica a quale Attività vuole iscriversi.
7.	Il Responsabile inserisce l’adesione dell’Allievo.
I passi 5-7 si ripetono per ogni Attività.
8.	Il Sistema salva l’iscrizione.
Estensioni:
7a. Allievo già registrato all’Attività:
1.	Il Sistema non permette all’Allievo di registrarsi
2.	Il Sistema mostra un messaggio di errore
7b. Numero limite partecipazioni raggiunto
1.	Il Sistema non permette all’Allievo di registrarsi
2.	Il Sistema mostra un messaggio di errore


Caso UC3: Aggiunta di una nuova Attività 

Attore primario: Responsabile del centro

Scenario principale di successo:
1.	Il Responsabile vuole aggiungere una nuova Attività tra quelle disponibili per quel Centro.
2.	Il Responsabile inizia l’aggiunta di una nuova Attività.
3.	Il Responsabile inserisce i dati della nuova Attività.
4.	Il Responsabile conclude l’inserimento.
5.	Il Sistema salva la nuova Attività.



Caso UC4: Visione statistiche Attività del Centro

Attore primario: Responsabile del centro

Scenario principale di successo:
1.	Il Responsabile vuole vedere le Attività svolte nel Centro con le relative partecipazioni.
2.	Il Responsabile accede alla visione statistiche.
3.	Il Sistema mostra l’elenco delle Attività, con la relativa partecipazione.


Caso UC5: Visualizzazione Attività dei Centri in un periodo di tempo (compreso tra due date) 

Attore primario: Direttore dell’azienda

Scenario principale di successo:
1.	Il Direttore vuole vedere le Attività dei Centri in un periodo di tempo .
2.	Il Direttore accede alla gestione dei Centri.
3.	Il Sistema mostra la lista dei centri con il relativo numero di iscritti (recenti e totali) e affluenza alle Attività in quel periodo di tempo.
4.	Il Direttore accede al singolo Centro.
5.	Il Sistema mostra le partecipazioni alle singole Attività da parte degli Allievi.


Caso UC6: Aggiunta di un nuovo Centro 

Attore primario: Direttore dell’azienda

Scenario principale di successo:
1.	Il Direttore vuole aggiungere un nuovo Centro di formazione.
2.	Il Direttore inizia l’inserimento di un nuovo Centro di formazione.
3.	Il Direttore inserisce i dati relativi al nuovo Centro.
4.	Il Direttore inserisce i dati relativi al Responsabile del Centro.
5.	Il Sistema salva il nuovo Centro in modo opportuno.
Estensioni:
3a. Centro già registrato precedentemente:
1.	Il Sistema recupera le informazioni associate al Centro
2.	Il Sistema mostra tali informazioni
4a. Responsabile già selezionato per altro Centro
1.	Il Sistema mostra un messaggio di errore
2.	Il Sistema mostra il riferimento del Centro di cui è responsabile



