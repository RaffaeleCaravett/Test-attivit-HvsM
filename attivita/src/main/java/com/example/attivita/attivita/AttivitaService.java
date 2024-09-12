package com.example.attivita.attivita;

import com.example.attivita.categoria.Categoria;
import com.example.attivita.categoria.CategoriaRepository;
import com.example.attivita.exceptions.AttivitaNotFoundException;
import com.example.attivita.exceptions.BadRequestException;
import com.example.attivita.exceptions.CategoriaNotFoundException;
import com.example.attivita.payloads.entities.AttivitaDTO;
import com.example.attivita.prenotazione.Prenotazione;
import com.example.attivita.prenotazione.PrenotazioneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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
    @Autowired
    PrenotazioneRepository prenotazioneRepository;
    public Attivita save(AttivitaDTO attivitaDTO, MultipartFile file) {
            Attivita attivita = new Attivita();

            attivita.setDate(LocalDate.parse(attivitaDTO.data()));
            attivita.setNome(attivitaDTO.nome());
            attivita.setLuogo(attivitaDTO.luogo());
            attivita.setDisponibilita(true);

            List<Categoria> categoriaList = new ArrayList<>();
            for (long l : attivitaDTO.categorie_id()) {
                Categoria categoria = categoriaRepository.findById(l).orElseThrow(() -> new CategoriaNotFoundException("Categoria con id " + l + " non trovata in db."));
                categoriaList.add(categoria);
            }
            attivita.setCategoriaList(categoriaList);
            attivita.setOraInizio(LocalTime.parse(attivitaDTO.oraInizio()));
            attivita.setOraFine(LocalTime.parse(attivitaDTO.oraFine()));
            attivita.setPostiDisponibili(attivitaDTO.postiDisponibili());
            attivita.setPostiOccupati(0);

            return attivitaRepository.save(attivita);

    }

    public Attivita putById(long id, AttivitaDTO attivitaDTO) {
            Attivita attivita = attivitaRepository.findById(id).orElseThrow(() -> new AttivitaNotFoundException("Attività con id " + id + " non trovata in db."));

            attivita.setDate(LocalDate.parse(attivitaDTO.data()));
            attivita.setNome(attivitaDTO.nome());
            attivita.setLuogo(attivitaDTO.luogo());
            attivita.setDisponibilita(true);

            List<Categoria> categoriaList = new ArrayList<>();
            for (long l : attivitaDTO.categorie_id()) {
                Categoria categoria = categoriaRepository.findById(l).orElseThrow(() -> new CategoriaNotFoundException("Categoria con id " + l + " non trovata in db."));
                categoriaList.add(categoria);
            }
            attivita.setCategoriaList(categoriaList);
            attivita.setOraInizio(LocalTime.parse(attivitaDTO.oraInizio()));
            attivita.setOraFine(LocalTime.parse(attivitaDTO.oraFine()));
            attivita.setPostiDisponibili(attivitaDTO.postiDisponibili());
            attivita.setPostiOccupati(0);

            return attivitaRepository.save(attivita);

    }
    public Attivita putImageById(long id,MultipartFile file){
            Attivita attivita = attivitaRepository.findById(id).orElseThrow(() -> new AttivitaNotFoundException("Attività con id " + id + " non trovata in db."));

    }

    public boolean deleteById(long id) {
        try {
            Attivita attivita = attivitaRepository.findById(id).orElseThrow(() -> new AttivitaNotFoundException("Attività con id " + id + " non trovata in db."));
            for(Prenotazione prenotazione : attivita.getPrenotazioneList()){
                prenotazioneRepository.deleteById(prenotazione.getId());
            }
            attivitaRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
    }

    public Page<Attivita> findByDisponibilità(boolean disponibilita, int page, int size, String orderBy,String direction) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(direction),orderBy));
        try {
            return attivitaRepository.findByDisponibilita(disponibilita, pageable);
        } catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
    }

    public Page<Attivita> findByData(String data, int page, int size, String orderBy,String direction) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(direction),orderBy));
        try {
            LocalDate localDate = LocalDate.parse(data);
            return attivitaRepository.findByDate(localDate, pageable);
        } catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
    }

    public Page<Attivita> findByNome(String nome, int page, int size, String orderBy,String direction) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(direction),orderBy));
        try {
            return attivitaRepository.findByNomeContaining(nome, pageable);
        } catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
    }
    public Page<Attivita> findByNomeAndDate(String nome,String date, int page, int size, String orderBy,String direction) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(direction),orderBy));
        try {
            LocalDate localDate = LocalDate.parse(date);
            return attivitaRepository.findByNomeContainingAndDate(nome,localDate, pageable);
        } catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
    }

    public Page<Attivita> findByCategoria(long id, int page, int size, String orderBy,String direction) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(direction),orderBy));
        try {
            return attivitaRepository.findByCategoriaList_Id(id, pageable);
        } catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
    }

    public Attivita findByPrenotazione(long id) {
        try {
            return attivitaRepository.findByPrenotazioneList_Id(id);
        } catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
    }
    public Page<Attivita> findByCategoriaNomeAndDisponibilita(long id, String nome, boolean disponibilita,int page, int size, String orderBy,String direction){
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(direction),orderBy));
        try {
            return attivitaRepository.findByCategoriaList_IdAndNomeContainingAndDisponibilita(id,nome,disponibilita,pageable);
        } catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
    }
    public Attivita findById(long id){
        return attivitaRepository.findById(id).orElseThrow(()->new AttivitaNotFoundException("Attività con id "+ id + " non trovata in db."));
    }
}
