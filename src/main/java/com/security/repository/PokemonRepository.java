package com.security.repository;

import com.security.entity.Pokemon;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PokemonRepository extends JpaRepository<Pokemon, Long> {
}
