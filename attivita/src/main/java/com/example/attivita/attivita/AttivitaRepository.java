package com.example.attivita.attivita;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Repository
@Transactional
public interface AttivitaRepository extends JpaRepository<Attivita,Long> {
    Optional<Attivita> findByPrenotazioneList_Id(long id);
    Page<Attivita> findByCategoriaList_Id(long id, Pageable pageable);
    Page<Attivita> findByCategoriaList_IdAndNomeContainingIgnoreCaseAndDisponibilita(long id,String nome,boolean disponibilita, Pageable pageable);
    Page<Attivita> findByDisponibilita(boolean disponibilita, Pageable pageable);
    Page<Attivita> findByNomeContainingIgnoreCase(String nome, Pageable pageable);
    Page<Attivita> findByNomeContainingIgnoreCaseAndDate(String nome,LocalDate data, Pageable pageable);
    Page<Attivita> findByDate(LocalDate localDate,Pageable pageable);
    Optional<Attivita> findByNomeAndLuogoAndOraInizio(String nome, String luogo, LocalTime oraInizio);

}
