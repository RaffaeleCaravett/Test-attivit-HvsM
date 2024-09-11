package com.example.attivita.payloads.entities;

import jakarta.validation.constraints.NotEmpty;

public record UserDTO(

        @NotEmpty(message = "Il nome è vuoto.")
        String nome,
        @NotEmpty(message = "Il cognome è vuoto.")
        String cognome,
        @NotEmpty(message = "L'email è vuota.")
        String email,
        @NotEmpty(message = "La password è vuota.")
        String password

) {

}
