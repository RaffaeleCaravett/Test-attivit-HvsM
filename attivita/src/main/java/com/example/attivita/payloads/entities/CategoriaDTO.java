package com.example.attivita.payloads.entities;

import jakarta.validation.constraints.NotEmpty;

public record CategoriaDTO(
        @NotEmpty(message = "Nome vuoto")
        String nome
) {
}
