package com.example.attivita.payloads.entities;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record AttivitaDTO(
       @NotEmpty(message = "Nome vuoto.")
       String nome,
       @NotEmpty(message = "Luogo vuoto.")
       String luogo,
       @NotEmpty(message = "Data vuota.")
       String data,
       @NotEmpty(message = "Ora inizio vuota.")
       String oraInizio,
       @NotEmpty(message = "Ora fine vuota.")
       String oraFine,
       @NotNull(message = "Posti disponibili vuoti.")
       int postiDisponibili,
       @NotNull(message = "Inserisci almeno una categoria.")
       List<Long> categorie_id

) {
}
