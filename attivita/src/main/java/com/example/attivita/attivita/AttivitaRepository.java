package com.example.attivita.attivita;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Locale;

@Repository
public interface AttivitaRepository extends JpaRepository<Attivita,Long> {
    Attivita findByPrenotazioneList_Id(long id);
    Page<Attivita> findByCategoriaList_Id(long id, Pageable pageable);
    Page<Attivita> findByCategoriaList_IdAndNomeContainingAndDisponibilita(long id,String nome,boolean disponibilita, Pageable pageable);
    Page<Attivita> findByDisponibilita(boolean disponibilita, Pageable pageable);
    Page<Attivita> findByNomeContaining(String nome, Pageable pageable);
    Page<Attivita> findByNomeContainingAndDate(String nome,LocalDate data, Pageable pageable);
    Page<Attivita> findByDate(LocalDate localDate,Pageable pageable);

}
