package com.security.service;

import com.security.dto.PokemonDTO;
import com.security.entity.Pokemon;
import com.security.repository.PokemonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class TyradexService {

    private final PokemonRepository pokemonRepository;

    private static final String BASE_URL = "https://tyradex.vercel.app/api/v1/pokemon/";

    private final RestTemplate restTemplate = new RestTemplate();

    public Pokemon scrapPokemon(String name) {
        String url = BASE_URL + name;

        // Appel de l'API Tyradex pour récupérer les informations du Pokémon
        PokemonDTO pokemonDTO = restTemplate.getForObject(url, PokemonDTO.class);

        if (pokemonDTO != null) {
            // Mapper PokemonDTO vers Pokemon
            Pokemon pokemon = new Pokemon();
            pokemon.setPokedexId(pokemonDTO.getPokedex_id());
            pokemon.setGeneration(pokemonDTO.getGeneration());
            pokemon.setCategory(pokemonDTO.getCategory());
            pokemon.setNameFr(pokemonDTO.getName().get("fr"));
            pokemon.setSpriteUrl(pokemonDTO.getSprites().getRegular());
            pokemon.setType(pokemonDTO.getTypes().get(0).getName()); // On prend le premier type (si applicable)

            // Stats
            pokemon.setHp(pokemonDTO.getStats().getHp());
            pokemon.setAttack(pokemonDTO.getStats().getAtk());
            pokemon.setDefense(pokemonDTO.getStats().getDef());
            pokemon.setSpecialAttack(pokemonDTO.getStats().getSpe_atk());
            pokemon.setSpecialDefense(pokemonDTO.getStats().getSpe_def());
            pokemon.setSpeed(pokemonDTO.getStats().getVit());

            // Sauvegarde du Pokémon dans la base de données
            return pokemonRepository.save(pokemon);
        } else {
            throw new RuntimeException("Pokémon non trouvé");
        }
    }

    public Pokemon scrapPokemon(Long id) {
        // Appel de l'API Tyradex pour récupérer les informations du Pokémon
        PokemonDTO pokemonDTO = restTemplate.getForObject(BASE_URL + id, PokemonDTO.class);

        if (pokemonDTO != null) {
            // Mapper PokemonDTO vers Pokemon
            Pokemon pokemon = new Pokemon();
            pokemon.setPokedexId(pokemonDTO.getPokedex_id());
            pokemon.setGeneration(pokemonDTO.getGeneration());
            pokemon.setCategory(pokemonDTO.getCategory());
            pokemon.setNameFr(pokemonDTO.getName().get("fr"));
            pokemon.setSpriteUrl(pokemonDTO.getSprites().getRegular());
            pokemon.setType(pokemonDTO.getTypes().get(0).getName()); // On prend le premier type (si applicable)

            // Stats
            pokemon.setHp(pokemonDTO.getStats().getHp());
            pokemon.setAttack(pokemonDTO.getStats().getAtk());
            pokemon.setDefense(pokemonDTO.getStats().getDef());
            pokemon.setSpecialAttack(pokemonDTO.getStats().getSpe_atk());
            pokemon.setSpecialDefense(pokemonDTO.getStats().getSpe_def());
            pokemon.setSpeed(pokemonDTO.getStats().getVit());

            // Sauvegarde du Pokémon dans la base de données
            return pokemonRepository.save(pokemon);
        } else {
            throw new RuntimeException("Pokémon non trouvé");
        }
    }
    public String reboundRequest(Long id) {
        String response = restTemplate.getForObject(BASE_URL + id, String.class);
        return "Rebond de la requête vers Tyradex : " + response;
    }
    public String reboundRequest(String name){
        String response = restTemplate.getForObject(BASE_URL + name, String.class);
        return "Rebond de la requête vers Tyradex : " + response;
    }
}