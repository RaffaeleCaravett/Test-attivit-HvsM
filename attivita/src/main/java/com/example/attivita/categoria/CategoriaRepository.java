package com.example.attivita.categoria;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria,Long> {
    Optional<Categoria> findByNome(String nome);
    List<Categoria> findByNomeContainingIgnoreCase(String nome);
    List<Categoria> findByAttivitaList_Id(long id);
}
