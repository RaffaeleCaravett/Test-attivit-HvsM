package com.example.attivita.attivita;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "attivita")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Attivita {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nome;
    private String luogo;
    private LocalDate date;
    private int oraInizio;
    private int oraFine;
    private int postiDisponibili;
    private int postiOccupati;

}
