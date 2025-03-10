package com.security.controller;

import com.security.entity.Pokemon;
import com.security.repository.PokemonRepository;
import com.security.service.TyradexService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pokemon")
@RequiredArgsConstructor
public class PokemonController {

    private final TyradexService tyradexService;
    private final PokemonRepository pokemonRepository;

    // 1.Reboudir (routes accessibles par ROLE_BOUNCER)
    // Rebondir une requête avec l'id du pokedex
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
    // 2.Scrapping (routes accessibles par ROLE_SCRAPER)
    // Lancer le scrapping avec le nom du pokedex
    @PostMapping("/scrap/name/{name}")
    @PreAuthorize("hasRole('ROLE_SCRAPPER')")
    public Pokemon scrapPokemon(@PathVariable String name) {
        return tyradexService.scrapPokemon(name);
    }
    // Lancer le scrapping avec l'id du pokemon
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
    public ResponseEntity<String> deletePokemon(@PathVariable Long id) {
        pokemonRepository.deleteById(id);
        return ResponseEntity.ok("Le Pokémon a été supprimer avec succès.");
    }

    // Ajouter un Pokémon dans la base de données
    @PostMapping
    @PreAuthorize("hasRole('ROLE_CRUDER')")
    public ResponseEntity<String> createPokemon(@RequestBody Pokemon pokemon) {
        pokemonRepository.save(pokemon);
        return ResponseEntity.ok("Le Pokémon a été ajouter avec succès.");
    }
    // Modifier un Pokémon existant
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_CRUDER')")
    public ResponseEntity<String> updatePokemon(@PathVariable Long id, @RequestBody Pokemon pokemonDetails) {
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

            pokemonRepository.save(pokemon); // Sauvegarder la mise à jour

            // Retourner une réponse avec un message de confirmation
            return ResponseEntity.ok("Le Pokémon avec l'ID " + id + " a été modifié avec succès.");
        }).orElseThrow(() -> new RuntimeException("Pokemon non trouvé"));
    }

}