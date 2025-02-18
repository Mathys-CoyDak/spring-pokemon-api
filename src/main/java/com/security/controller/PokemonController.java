package com.security.controller;

import com.security.entity.Pokemon;
import com.security.repository.PokemonRepository;
import com.security.service.TyradexService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pokemon")
@RequiredArgsConstructor
public class PokemonController {

    private final TyradexService tyradexService;
    private final PokemonRepository pokemonRepository;

    // Ajouter un Pokémon depuis Tyradex
    @PostMapping("/name/{name}")
    public Pokemon addPokemon(@PathVariable String name) {
        return tyradexService.scrapPokemon(name);  // Enregistre et renvoie le Pokémon
    }

    // 1. Rebondir une requête (route accessible par ROLE_BOUNCER)
    @GetMapping("/rebound/{id}")
    @PreAuthorize("hasRole('ROLE_BOUNCER')")
    public String reboundRequest(@PathVariable Long id) {
        // Code pour rebondir la requête
        return tyradexService.reboundRequest(id);
    }

    // 2. Lancer le scrapping (route accessible par ROLE_SCRAPPER)
    @PostMapping("/scrap/{name}")
    @PreAuthorize("hasRole('ROLE_SCRAPPER')")
    public Pokemon scrapPokemon(@PathVariable String name) {
        return tyradexService.scrapPokemon(name);
    }

    // 3. Utiliser le CRUD (routes accessibles par ROLE_CRUDER)
    // Lire tous les Pokémon
    @GetMapping
    @PreAuthorize("hasRole('ROLE_CRUDER')")
    public List<Pokemon> getAllPokemon() {
        return pokemonRepository.findAll();
    }

    // Lire un Pokémon par ID
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_CRUDER')")
    public Pokemon getPokemonById(@PathVariable Long id) {
        return pokemonRepository.findById(id).orElseThrow();
    }

    // Supprimer un Pokémon (CRUD)
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_CRUDER')")
    public void deletePokemon(@PathVariable Long id) {
        pokemonRepository.deleteById(id);
    }
}