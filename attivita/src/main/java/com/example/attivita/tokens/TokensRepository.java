package com.example.attivita.tokens;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokensRepository extends JpaRepository<Tokens,Long> {
}
