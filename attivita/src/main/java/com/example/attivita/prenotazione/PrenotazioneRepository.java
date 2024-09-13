package com.example.attivita.prenotazione;

import com.example.attivita.enums.StatoPrenotazione;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional
public interface PrenotazioneRepository extends JpaRepository<Prenotazione,Long> {
    Page<Prenotazione> findByUser_Id(long id, Pageable pageable);
    Optional<Prenotazione> findByUser_IdAndAttivita_Id(long userId, long attivitaId);
    Optional<Prenotazione> findByUser_IdAndAttivita_IdAndStatoPrenotazione(long userId, long attivitaId, StatoPrenotazione statoPrenotazione);

    Page<Prenotazione> findByAttivita_Id(long id, Pageable pageable);
    Page<Prenotazione> findByStatoPrenotazione(StatoPrenotazione statoPrenotazione,Pageable pageable);
}
