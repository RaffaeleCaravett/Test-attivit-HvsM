package com.example.attivita.prenotazione;

import com.example.attivita.attivita.AttivitaRepository;
import com.example.attivita.enums.StatoPrenotazione;
import com.example.attivita.exceptions.AttivitaNotFoundException;
import com.example.attivita.exceptions.PrenotazioneAlreadyExistsException;
import com.example.attivita.exceptions.UserNotFoundException;
import com.example.attivita.payloads.entities.PrenotazioneDTO;
import com.example.attivita.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PrenotazioneService {

    @Autowired
    PrenotazioneRepository prenotazioneRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    AttivitaRepository attivitaRepository;

    public Prenotazione save(PrenotazioneDTO prenotazioneDTO){
        Prenotazione prenotazione = new Prenotazione();

        Optional<Prenotazione> prenotazione1 = prenotazioneRepository.findByUser_IdAndAttivita_IdAndStatoPrenotazione(prenotazioneDTO.user_id(), prenotazioneDTO.attivita_id(), StatoPrenotazione.attiva);
        if(prenotazione1.isPresent()){
            throw new PrenotazioneAlreadyExistsException("Prenotazione già esistente in db.");
        }
        prenotazione.setStatoPrenotazione(StatoPrenotazione.attiva);
        prenotazione.setUser(userRepository.findById(prenotazioneDTO.user_id()).orElseThrow(() -> new UserNotFoundException("User con id " + prenotazioneDTO.user_id() + " non trovato in Database.")));
        prenotazione.setAttivita(attivitaRepository.findById(prenotazioneDTO.attivita_id()).orElseThrow(() -> new AttivitaNotFoundException("Attività con id " + prenotazioneDTO.attivita_id() + " non trovata in db.")));

    }
}
