package com.example.attivita.payloads.entities;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record PrenotazioneDTO (
        String stato,
        @NotNull(message= "user_id vuoto")
        long user_id,
        @NotNull(message= "attivita_id vuoto")
        long attivita_id
){
}
