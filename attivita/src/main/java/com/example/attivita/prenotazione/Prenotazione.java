package com.example.attivita.prenotazione;

import com.example.attivita.attivita.Attivita;
import com.example.attivita.enums.StatoPrenotazione;
import com.example.attivita.interfaces.PrenotazioneCheck;
import com.example.attivita.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "prenotazione")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Prenotazione implements PrenotazioneCheck {
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

    @Override
    public void isValid() {
        if(this.attivita.getDate().isBefore(LocalDate.now())||this.attivita.getDate().isEqual(LocalDate.now())&&this.attivita.getOraInizio().isBefore(LocalTime.now())){
            this.setStatoPrenotazione(StatoPrenotazione.scaduta);
        }
    }
}
