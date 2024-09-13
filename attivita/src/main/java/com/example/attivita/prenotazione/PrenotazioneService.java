package com.example.attivita.prenotazione;

import com.example.attivita.attivita.Attivita;
import com.example.attivita.attivita.AttivitaRepository;
import com.example.attivita.enums.StatoPrenotazione;
import com.example.attivita.exceptions.*;
import com.example.attivita.payloads.entities.PrenotazioneDTO;
import com.example.attivita.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

@Service
public class PrenotazioneService {

    @Autowired
    PrenotazioneRepository prenotazioneRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    AttivitaRepository attivitaRepository;

    public Prenotazione save(PrenotazioneDTO prenotazioneDTO) {
        Prenotazione prenotazione = new Prenotazione();
        Attivita attivita = attivitaRepository.findById(prenotazioneDTO.attivita_id()).orElseThrow(() -> new AttivitaNotFoundException("Attività con id " + prenotazioneDTO.attivita_id() + " non trovata in db."));
        Optional<Prenotazione> prenotazione1 = prenotazioneRepository.findByUser_IdAndAttivita_IdAndStatoPrenotazione(prenotazioneDTO.user_id(), prenotazioneDTO.attivita_id(), StatoPrenotazione.attiva);
        if (prenotazione1.isPresent()) {
            throw new PrenotazioneAlreadyExistsException("Prenotazione già esistente in db.");
        }
        prenotazione.setStatoPrenotazione(StatoPrenotazione.attiva);
        prenotazione.setUser(userRepository.findById(prenotazioneDTO.user_id()).orElseThrow(() -> new UserNotFoundException("User con id " + prenotazioneDTO.user_id() + " non trovato in Database.")));
        prenotazione.setAttivita(attivita);

        if(attivita.getDate().isBefore(LocalDate.now())||attivita.getDate().isEqual(LocalDate.now())&&attivita.getOraInizio().isBefore(LocalTime.now())){
            throw new AttivitaHasPassedException("Non è più possibile prenotare per questa attività");
        }else if(attivita.getPostiDisponibili()==attivita.getPostiOccupati()){
            throw new AttivitaHasPassedException("Non è più possibile prenotare per questa attività");
        }else{
            attivita.setPostiOccupati(attivita.getPostiOccupati()+1);
        }

        return prenotazioneRepository.save(prenotazione);
    }

    public boolean delete(long uId, long id) {
        Optional<Prenotazione> prenotazione = prenotazioneRepository.findById(id);

        if (prenotazione.isPresent() && prenotazione.get().getUser().getId() == uId) {
            Attivita attivita = attivitaRepository.findById(prenotazione.get().getAttivita().getId()).orElseThrow(() -> new AttivitaNotFoundException("Attività con id " + prenotazione.get().getAttivita().getId() + " non trovata in db."));
            if(attivita.getDate().isBefore(LocalDate.now())||attivita.getDate().isEqual(LocalDate.now())&&attivita.getOraInizio().isBefore(LocalTime.now())){
                System.out.println("Attività has passed.");
            }else if(attivita.getPostiDisponibili()==attivita.getPostiOccupati()){
                System.out.println("Attività has passed.");
            }else{
                attivita.setPostiOccupati(attivita.getPostiOccupati()-1);
            }
            prenotazioneRepository.delete(prenotazione.get());
            return true;
        } else {
            if (!prenotazione.isPresent()) {
                throw new PrenotazioneNotFoundException("Prenotazione con id " + id + " non trovata in db");
            } else {
                throw new AccessDeniedException("Non hai effettuato questa prenotazione");
            }
        }
    }

    public Page<Prenotazione> getByUserId(long userId, int page, int size, String orderBy, String direction) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(direction), orderBy));
        return prenotazioneRepository.findByUser_Id(userId, pageable);
    }

    public Page<Prenotazione> getByAttivitaId(long attivitaId, int page, int size, String orderBy, String direction) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(direction), orderBy));
        return prenotazioneRepository.findByAttivita_Id(attivitaId, pageable);
    }

    public Prenotazione getByUserIdAndAttivitaId(long userId, long attivitaId) {
        return prenotazioneRepository.findByUser_IdAndAttivita_Id(userId, attivitaId).orElseThrow(() -> new PrenotazioneNotFoundException("Prenotazione non trovata in db"));
    }

    public Prenotazione getByUserIdAttivitaIdAndStato(long uId, long aId, String stato) {
        StatoPrenotazione statoPrenotazione = StatoPrenotazione.valueOf(stato);

        return prenotazioneRepository.findByUser_IdAndAttivita_IdAndStatoPrenotazione(uId, aId, statoPrenotazione).orElseThrow(() -> new PrenotazioneNotFoundException("Prenotazione non trovata in db"));
    }
    public Page<Prenotazione> findByStatoPrenotazione(String statoPrenotazione,int page, int size, String orderBy, String direction){
        StatoPrenotazione statoPrenotazione1 = StatoPrenotazione.valueOf(statoPrenotazione);
        Pageable pageable = PageRequest.of(page,size,Sort.by(Sort.Direction.fromString(direction),orderBy));

        return prenotazioneRepository.findByStatoPrenotazione(statoPrenotazione1,pageable);
    }
    public Long deleteAllByUId(long userId){
        return prenotazioneRepository.deleteByUser_Id(userId);
    }

}
