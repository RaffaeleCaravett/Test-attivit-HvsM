package com.example.attivita.prenotazione;

import com.example.attivita.attivita.Attivita;
import com.example.attivita.enums.StatoPrenotazione;
import com.example.attivita.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "prenotazione")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Prenotazione {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "attivita_id")
    private Attivita attivita;
    @Enumerated(EnumType.STRING)
    private StatoPrenotazione statoPrenotazione;
}
