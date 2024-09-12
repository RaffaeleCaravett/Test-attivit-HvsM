package com.example.attivita.attivita;

import com.example.attivita.categoria.Categoria;
import com.example.attivita.prenotazione.Prenotazione;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
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
    private LocalTime oraInizio;
    private LocalTime oraFine;
    private int postiDisponibili;
    private int postiOccupati;
    @OneToMany(mappedBy = "attivita")
    @JsonIgnore
    private List<Prenotazione> prenotazioneList;
    @ManyToMany
    @JoinTable(
            name = "attivita_categorie",
            joinColumns = @JoinColumn(name = "attivita_id"),
            inverseJoinColumns = @JoinColumn(name = "categoria_id"))
    private List<Categoria> categoriaList;
}
