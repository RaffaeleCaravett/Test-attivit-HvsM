package com.example.attivita.attivita;

import com.example.attivita.categoria.Categoria;
import com.example.attivita.categoria.CategoriaRepository;
import com.example.attivita.payloads.entities.AttivitaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class AttivitaService {
    @Autowired
    AttivitaRepository attivitaRepository;
    @Autowired
    CategoriaRepository categoriaRepository;

    public Attivita save(AttivitaDTO attivitaDTO){
        Attivita attivita = new Attivita();

        attivita.setDate(LocalDate.of(attivitaDTO.anno(),attivitaDTO.mese(),attivitaDTO.giorno()));
        attivita.setNome(attivitaDTO.nome());
        attivita.setLuogo(attivitaDTO.luogo());
        attivita.setDisponibilita(true);

        List<Categoria>  categoriaList = new ArrayList<>();
        for(long l : attivitaDTO.categorie_id()){
            Categoria categoria = cat
        }
    }
}
