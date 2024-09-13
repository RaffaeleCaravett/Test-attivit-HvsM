L'applicativo è stato creato utilizzando Java ed il framework Spring, insieme a Springboot, web, data , jpa, validation, lombok, jakarta, jwt, postgresql, json e commons.

Ho utilizzato Maven come strumento di build e Tomcat come server.

Ho strutturato l'applicativo con Entity, RestController, Service, Repository, ExceptionsHandler, RestControllerAdvice e altri Component.

L'utilizzo degli endpoint e di tutte le loro funzionalità è concesso tramite autorizzazione. Una volta che l'utente si registra e si logga tramite apposito controller ("/auth) che appartiene alla lista di endpoint che non vanno filtrati all'interno della filterchain,
il server restituisce un jwt token, da inserire nell'header della richiesta http come Bearer Token. Controllato il token (id,Expiration date e signature), l'utente avrà accesso o meno alle funzionalità. Oltre a questo, su alcuni metodi ho inserito un @PreAuthorize in modo
da autorizzare solo gli utenti di tipo admin a fare determinate cose, per esempio inserire un'attività.

La logica di business è contenuta nei service.
La metodologia di interrogazione del db relazionale postgres è tramite JPA naming convention.
I controller sono Rest, comunicano tramite json, hanno una request mapping, un method mapping e un request body o una response body.
Gli handler delle exceptions cercano di mappare tutte le exceptions del caso o quasi.
E' presente una interfaccia attraverso la quale si condivide un metodo comune e si fa @Override per controllare la validità della prenotazione.
Si utilizzano relazioni @OneToOne, @ManyToOne, @OneToMany e @ManyToMany con relativi FetchType.
C'è stato bisogno di usare l'annotazione @Transactional su repositories che condividono dati per i quali bisogna fare i commit delle transazioni.

E' possibile inserire le immagini nelle attività e completare tutte le crud operations di base su tutti gli endpoint (Eccetto quelli per cui non era strettamente necessario)
Ho utilizzato la ultima versione di Java e la 3.3.3 di Springboot.

Ho utilizzato un file env.properties che è volutamente pushato su questo repository per utilizzare delle variabili d'ambiente nell'application.properties.
Nella cartella docs si trova lo schema entità-relazioni (poi, in pratica, un po' modificato dall'originale)
Ho versionato il codice cercando di utilizzare un branche per ogni entity o per ogni sezione dell'applicazione, mergiando poi quando ogni feature del relativo branch era finita.

Mi è piaciuto molto sviluppare questo applicativo perchè si è dovuto tenere conto di alcune cose minuziose.
