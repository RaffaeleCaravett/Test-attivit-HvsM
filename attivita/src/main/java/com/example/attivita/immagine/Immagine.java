package com.example.attivita.immagine;

import com.example.attivita.attivita.Attivita;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "immagini")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Immagine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String type;
    @Lob
    @Column(name = "imagedata", length = 1000)
    private byte[] imageData;
    @OneToOne
    @JsonIgnore
    @JoinColumn(name = "attivita_id")
    private Attivita attivita;
}
