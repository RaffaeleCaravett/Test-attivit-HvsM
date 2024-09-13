package com.example.attivita.categoria;

import com.example.attivita.attivita.Attivita;
import com.example.attivita.attivita.AttivitaRepository;
import com.example.attivita.exceptions.BadRequestException;
import com.example.attivita.exceptions.CategoriaAlreadyExistsException;
import com.example.attivita.exceptions.CategoriaNotFoundException;
import com.example.attivita.exceptions.DTOHasErrorsException;
import com.example.attivita.payloads.entities.CategoriaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoriaService {

    @Autowired
    CategoriaRepository categoriaRepository;
    @Autowired
    AttivitaRepository attivitaRepository;

    public Categoria save(CategoriaDTO categoriaDTO){
        if(categoriaRepository.findByNome(categoriaDTO.nome()).isPresent()){
            throw new CategoriaAlreadyExistsException("Categoria con nome " + categoriaDTO.nome() + " già presente in db.");
        }
        Categoria categoria = new Categoria();
        categoria.setNome(categoriaDTO.nome());
        return categoriaRepository.save(categoria);
    }
    public Categoria putById(long id, CategoriaDTO categoriaDTO){
        Categoria categoria = categoriaRepository.findById(id).orElseThrow(()-> new CategoriaNotFoundException("Categoria con id "+ id + " non trovata in db."));
        if(!categoriaDTO.nome().equals(categoria.getNome())&&categoriaRepository.findByNome(categoriaDTO.nome()).isPresent()){
            throw new CategoriaAlreadyExistsException("Categoria con nome " + categoriaDTO.nome() + " già presente in db.");
        }
        categoria.setNome(categoriaDTO.nome());
        return categoriaRepository.save(categoria);
    }
    public boolean deleteById(long id){
        try {
            Categoria categoria = categoriaRepository.findById(id).orElseThrow(()->  new CategoriaNotFoundException("Categoria con id "+ id + " non trovata in db."));
            List<Attivita> attivitaList = new ArrayList<>(categoria.getAttivitaList());
            for(Attivita a : attivitaList){
                a.getCategoriaList().remove(categoria);
                attivitaRepository.save(a);
            }
            categoriaRepository.deleteById(id);
            return true;
        }catch (Exception e){
            throw new BadRequestException(e.getMessage());
        }
    }
    public List<Categoria> getAll(){
        return categoriaRepository.findAll();
    }
    public Categoria findById(long id){
        return categoriaRepository.findById(id).orElseThrow(()-> new CategoriaNotFoundException("Categoria con id "+ id + " non trovata in db."));
    }
    public List<Categoria> findByNome(String nome){
      return categoriaRepository.findByNomeContainingIgnoreCase(nome);
    }
    public List<Categoria> findByAttivitaId(long id){
        return categoriaRepository.findByAttivitaList_Id(id);
    }
}
