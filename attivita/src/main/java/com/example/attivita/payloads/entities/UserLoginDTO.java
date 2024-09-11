package com.example.attivita.payloads.entities;

import jakarta.validation.constraints.NotEmpty;

public record UserLoginDTO (
        @NotEmpty(message = "Email vuota.")
        String email,
        @NotEmpty(message = "Password vuota.")
        String password
){
}
