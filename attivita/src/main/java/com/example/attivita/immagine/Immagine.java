package com.example.attivita.immagine;

import com.example.attivita.attivita.Attivita;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "immagini")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Immagine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String type;
    @Lob
    @Column(name = "imagedata",length = 1000)
    private byte[] imageData;
    @OneToOne(fetch=FetchType.EAGER)
    @JsonIgnore
    @JoinColumn(name = "attivita_id")
    private Attivita attivita;
}
