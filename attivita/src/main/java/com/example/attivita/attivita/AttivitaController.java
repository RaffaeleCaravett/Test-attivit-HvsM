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
    
}
