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

    // Rebondir une requête avec l'id du pokedex (route accessible par ROLE_BOUNCER)
    @GetMapping("/rebound/id/{id}")
    @PreAuthorize("hasRole('ROLE_BOUNCER')")
    public String reboundRequest(@PathVariable Long id) {
        return tyradexService.reboundRequest(id);
    }
    // Rebonds une requête avec un nom de Pokémon
    @GetMapping("/rebound/name/{name}")
    @PreAuthorize("hasRole('ROLE_BOUNCER')")
    public String reboundPokemon(@PathVariable String name) {
        return tyradexService.reboundRequest(name);
    }
    // 2. Lancer le scrapping (route accessible par ROLE_SCRAPPER)
    @PostMapping("/scrap/name/{name}")
    @PreAuthorize("hasRole('ROLE_SCRAPPER')")
    public Pokemon scrapPokemon(@PathVariable String name) {
        return tyradexService.scrapPokemon(name);
    }
    // 2. Lancer le scrapping (route accessible par ROLE_SCRAPPER)
    @PostMapping("/scrap/id/{id}")
    @PreAuthorize("hasRole('ROLE_SCRAPPER')")
    public Pokemon scrapPokemon(@PathVariable Long id) {
        return tyradexService.scrapPokemon(id);
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

    // Ajouter un Pokémon dans la base de données
    @PostMapping
    @PreAuthorize("hasRole('ROLE_CRUDER')")
    public Pokemon createPokemon(@RequestBody Pokemon pokemon) {
        return pokemonRepository.save(pokemon);
    }
    // Modifier un Pokémon existant
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_CRUDER')")
    public Pokemon updatePokemon(@PathVariable Long id, @RequestBody Pokemon pokemonDetails) {
        return pokemonRepository.findById(id).map(pokemon -> {
            pokemon.setPokedexId(pokemonDetails.getPokedexId());
            pokemon.setGeneration(pokemonDetails.getGeneration());
            pokemon.setCategory(pokemonDetails.getCategory());
            pokemon.setNameFr(pokemonDetails.getNameFr());
            pokemon.setSpriteUrl(pokemonDetails.getSpriteUrl());
            pokemon.setType(pokemonDetails.getType());
            pokemon.setHp(pokemonDetails.getHp());
            pokemon.setAttack(pokemonDetails.getAttack());
            pokemon.setDefense(pokemonDetails.getDefense());
            pokemon.setSpecialAttack(pokemonDetails.getSpecialAttack());
            pokemon.setSpecialDefense(pokemonDetails.getSpecialDefense());
            pokemon.setSpeed(pokemonDetails.getSpeed());
            return pokemonRepository.save(pokemon);
        }).orElseThrow(() -> new RuntimeException("Pokemon non trouvé"));
    }

}