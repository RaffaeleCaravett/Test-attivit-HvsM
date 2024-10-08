package com.example.attivita.attivita;

import com.example.attivita.categoria.Categoria;
import com.example.attivita.immagine.Immagine;
import com.example.attivita.prenotazione.Prenotazione;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

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
    private boolean disponibilita;
    private byte[] imageData;
    @OneToOne(mappedBy = "attivita",fetch=FetchType.EAGER)
    private Immagine immagine;
    @OneToMany(mappedBy = "attivita",fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Prenotazione> prenotazioneList;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "attivita_categorie",
            joinColumns = @JoinColumn(name = "attivita_id"),
            inverseJoinColumns = @JoinColumn(name = "categoria_id"))
    private List<Categoria> categoriaList;
}
