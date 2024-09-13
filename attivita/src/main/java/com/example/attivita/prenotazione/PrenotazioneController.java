package com.example.attivita.prenotazione;

import com.example.attivita.exceptions.DTOHasErrorsException;
import com.example.attivita.payloads.entities.PrenotazioneDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/prenotazione")
public class PrenotazioneController {

    @Autowired
    PrenotazioneService prenotazioneService;


    @PostMapping("")
    public Prenotazione save(@RequestBody @Validated PrenotazioneDTO prenotazioneDTO, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new DTOHasErrorsException(bindingResult.getAllErrors());
        }
        return prenotazioneService.save(prenotazioneDTO);
    }
    @GetMapping("/byUser/{id}")
    public Page<Prenotazione> findByUserId(@PathVariable long id , @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "id") String orderBy, @RequestParam(defaultValue = "DESC") String direction){
        return prenotazioneService.getByUserId(id,page,size,orderBy,direction);
    }
    @GetMapping("/byAttivita/{id}")
    public Page<Prenotazione> findByAttivitaId(@PathVariable long id , @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "id") String orderBy, @RequestParam(defaultValue = "DESC") String direction){
        return prenotazioneService.getByAttivitaId(id,page,size,orderBy,direction);
    }
    @GetMapping("/byUserAndAttivita/{id}/{attivitaId}")
    public Prenotazione findByUserIdAndAttivita(@PathVariable long id,@PathVariable long attivitaId){
        return prenotazioneService.getByUserIdAndAttivitaId(id,attivitaId);
    }
    @GetMapping("/byUserAttivitaAndStato/{id}/{attivitaId}/{stato}")
    public Prenotazione findByUserIdAndAttivitaIdAndStato(@PathVariable long id,@PathVariable long attivitaId,@PathVariable String stato){
        return prenotazioneService.getByUserIdAttivitaIdAndStato(id,attivitaId,stato);
    }
    @GetMapping("/byStato/{stato}")
    public Page<Prenotazione> findByStato(@PathVariable String stato, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "id") String orderBy, @RequestParam(defaultValue = "DESC") String direction){
        return prenotazioneService.findByStatoPrenotazione(stato,page,size,orderBy,direction);
    }

    @DeleteMapping("/{id}/{userId}")
    public boolean deleteById(@PathVariable long id, @PathVariable long userId){
        return prenotazioneService.delete(userId,id);
    }
    @DeleteMapping("/all/{userId}")
    public Long deleteAllByUId(@PathVariable long userId){
        return prenotazioneService.deleteAllByUId(userId);
    }
}
