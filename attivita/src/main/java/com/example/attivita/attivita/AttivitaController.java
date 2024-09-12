package com.example.attivita.attivita;

import com.example.attivita.exceptions.DTOHasErrorsException;
import com.example.attivita.payloads.entities.AttivitaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/attivita")
public class AttivitaController {
    @Autowired
    AttivitaService attivitaService;

    @PostMapping("")
    @PreAuthorize("hasAuthority('admin')")
    public Attivita save(@RequestBody @Validated AttivitaDTO attivitaDTO, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new DTOHasErrorsException(bindingResult.getAllErrors());
        }
        return attivitaService.save(attivitaDTO);
    }
    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('admin')")
    public Attivita putById(@PathVariable long id, @RequestBody @Validated AttivitaDTO attivitaDTO, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new DTOHasErrorsException(bindingResult.getAllErrors());
        }
        return attivitaService.putById(id,attivitaDTO);
    }
    @GetMapping("/{id}")
    public Attivita getById(@PathVariable long id){
        return attivitaService.findById(id);
    }

    @GetMapping("/byNomeContaining/{nome}")
    public Page<Attivita> getByNome(@PathVariable String nome, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "id") String orderBy,@RequestParam(defaultValue = "DESC") String direction){
      return attivitaService.findByNome(nome,page,size,orderBy,direction);
    }
    @GetMapping("/byNomeContainingAndDate/{nome}/{date}")
    public Page<Attivita> getByNome(@PathVariable String nome, @PathVariable String date, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "id") String orderBy,@RequestParam(defaultValue = "DESC") String direction){
        return attivitaService.findByNomeAndDate(nome,date,page,size,orderBy,direction);
    }
    @GetMapping("/byPrenotazioneId/{id}")
    public Attivita getByPrenotazione(@PathVariable long id){
        return attivitaService.findByPrenotazione(id);
    }
    @GetMapping("/byCategoria/{id}")
    public Page<Attivita> getByCategoria(@PathVariable long id, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "id") String orderBy,@RequestParam(defaultValue = "DESC") String direction){
        return attivitaService.findByCategoria(id,page,size,orderBy,direction);
    }
    @GetMapping("/byCategoriaNomeAndDisponibilita/{id}/{nome}/{disponibilita}")
    public Page<Attivita> getByCateNomeAndDisp(@PathVariable long id,@PathVariable String nome,@PathVariable boolean disponibilita, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "id") String orderBy,@RequestParam(defaultValue = "DESC") String direction){
        return attivitaService.findByCategoriaNomeAndDisponibilita(id,nome,disponibilita,page,size,orderBy,direction);
    }
    @GetMapping("/byDisponibilita/{disponibilita}")
    public Page<Attivita> getByNome(@PathVariable boolean disponibilita, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "id") String orderBy,@RequestParam(defaultValue = "DESC") String direction){
        return attivitaService.findByDisponibilità(disponibilita,page,size,orderBy,direction);
    }
    @GetMapping("/byData/{data}")
    public Page<Attivita> getByData(@PathVariable String data, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "id") String orderBy,@RequestParam(defaultValue = "DESC") String direction){
        return attivitaService.findByData(data,page,size,orderBy,direction);
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('admin')")
    public boolean deleteById(@PathVariable long id){
        return attivitaService.deleteById(id);
    }
}
