package com.security.repository;

import com.security.entity.Pokemon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface PokemonRepository extends JpaRepository<Pokemon, Long> {
    //Optional<Object> findByName(String name);
}
