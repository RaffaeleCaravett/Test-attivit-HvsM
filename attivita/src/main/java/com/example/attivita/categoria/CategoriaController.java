package com.example.attivita.categoria;

import com.example.attivita.exceptions.DTOHasErrorsException;
import com.example.attivita.payloads.entities.CategoriaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {
    @Autowired
    CategoriaService categoriaService;

    @PostMapping("")
    @PreAuthorize("hasAuthority('admin')")
    public Categoria save(@RequestBody @Validated CategoriaDTO categoriaDTO, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new DTOHasErrorsException(bindingResult.getAllErrors());
        }
        return categoriaService.save(categoriaDTO);
    }
    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('admin')")
    public Categoria putById(@PathVariable long id, @RequestBody @Validated CategoriaDTO categoriaDTO, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new DTOHasErrorsException(bindingResult.getAllErrors());
        }
        return categoriaService.putById(id,categoriaDTO);
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('admin')")
    public boolean save(@PathVariable long id){
        return categoriaService.deleteById(id);
    }

    @GetMapping("")
    public List<Categoria> findAll(){
        return categoriaService.getAll();
    }
    @GetMapping("/{id}")
    public Categoria findById(@PathVariable long id){
        return categoriaService.findById(id);
    }
    @GetMapping("/byAttivita/{id}")
    public List<Categoria> findByAttivita(@PathVariable long id){
        return categoriaService.findByAttivitaId(id);
    }
    @GetMapping("/nome/{nome}")
    public List<Categoria> findByNome(@PathVariable String nome){
        return categoriaService.findByNome(nome);
    }
}
