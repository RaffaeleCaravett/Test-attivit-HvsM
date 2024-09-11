package com.example.attivita.payloads.entities;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record AttivitaDTO(
       @NotEmpty(message = "Nome vuoto.")
       String nome,
       @NotEmpty(message = "Luogo vuoto.")
       String luogo,
       @NotNull(message = "Giorno vuoto.")
       int giorno,
       @NotNull(message = "Mese vuoto.")
       int mese,
       @NotNull(message = "Anno vuoto.")
       int anno,
       @NotNull(message = "Ora inizio vuota.")
       int oraInizio,
       @NotNull(message = "Ora fine vuota.")
       int oraFine,
       @NotNull(message = "Posti disponibili vuoti.")
       int postiDisponibili,
       @NotNull(message = "Inserisci almeno una categoria.")
       List<Long> categorie_id

) {
}
