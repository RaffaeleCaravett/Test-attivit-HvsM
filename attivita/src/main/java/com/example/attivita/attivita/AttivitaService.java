package com.example.attivita.attivita;

import com.example.attivita.categoria.Categoria;
import com.example.attivita.categoria.CategoriaRepository;
import com.example.attivita.exceptions.BadRequestException;
import com.example.attivita.exceptions.CategoriaNotFoundException;
import com.example.attivita.payloads.entities.AttivitaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class AttivitaService {
    @Autowired
    AttivitaRepository attivitaRepository;
    @Autowired
    CategoriaRepository categoriaRepository;

    public Attivita save(AttivitaDTO attivitaDTO){
        try
        {
        Attivita attivita = new Attivita();

        attivita.setDate(LocalDate.parse(attivitaDTO.data()));
        attivita.setNome(attivitaDTO.nome());
        attivita.setLuogo(attivitaDTO.luogo());
        attivita.setDisponibilita(true);

        List<Categoria>  categoriaList = new ArrayList<>();
        for(long l : attivitaDTO.categorie_id()){
            Categoria categoria = categoriaRepository.findById(l).orElseThrow(()->new CategoriaNotFoundException("Categoria con id "+ l + " non trovata in db."));
            categoriaList.add(categoria);
        }
        attivita.setCategoriaList(categoriaList);
        attivita.setOraInizio(LocalTime.parse(attivitaDTO.oraInizio()));
        attivita.setOraFine(LocalTime.parse(attivitaDTO.oraFine()));
        attivita.setPostiDisponibili(attivitaDTO.postiDisponibili());
        attivita.setPostiOccupati(0);

        return attivitaRepository.save(attivita);
        }catch (Exception e){
            throw new BadRequestException(e.getMessage());
        }
    }

    public Attivita putById(long id, AttivitaDTO attivitaDTO){
        try {
            Attivita attivita = attivitaRepository.findById(id).orElseThrow(()->new AttivitaNotFoundException("Attività con id "+ id + " non trovata in db.");
        }
    }
}
