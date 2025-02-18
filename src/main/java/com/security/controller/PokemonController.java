package com.security.controller;

import com.security.entity.Pokemon;
import com.security.repository.PokemonRepository;
import com.security.service.TyradexService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pokemon")
@RequiredArgsConstructor
public class PokemonController {
    private final TyradexService tyradexService;
    private final PokemonRepository pokemonRepository;

    // Ajouter un Pokémon depuis Tyradex
    @PostMapping("/{name}")
    public Pokemon addPokemon(@PathVariable String name) {
        return tyradexService.savePokemon(name);
    }

    // Lire tous les Pokémon
    @GetMapping
    public List<Pokemon> getAllPokemon() {
        return pokemonRepository.findAll();
    }

    // Lire un Pokémon par ID
    @GetMapping("/{id}")
    public Pokemon getPokemonById(@PathVariable Long id) {
        return pokemonRepository.findById(id).orElseThrow();
    }

    // Supprimer un Pokémon
    @DeleteMapping("/{id}")
    public void deletePokemon(@PathVariable Long id) {
        pokemonRepository.deleteById(id);
    }

    // Endpoint pour récupérer la liste des Pokémon d'une génération
    @GetMapping("/gen/{generation}")
    public String getPokemonsByGeneration(@PathVariable int generation) {
        return tyradexService.fetchPokemonByGeneration(generation);
    }
}
