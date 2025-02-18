package com.security.service;

import com.security.entity.Pokemon;
import com.security.repository.PokemonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;

@Service
@RequiredArgsConstructor
public class TyradexService {
    private final PokemonRepository pokemonRepository;

    private static final String BASE_URL = "https://tyradex.vercel.app/api/v1";

    private final RestClient restClient = RestClient.builder().baseUrl(BASE_URL).build();

    public Pokemon fetchPokemonData(String name) {
        return restClient.get()
                .uri("/pokemon/{name}", name)
                .retrieve()
                .body(Pokemon.class);
    }

    public String fetchPokemonByGeneration(int generation) {
        try {
            return restClient.get()
                    .uri("/gen/{generation}", generation)
                    .retrieve()
                    .body(String.class);  // On récupère la réponse en tant que String
        } catch (RestClientException e) {
            throw new RuntimeException("Erreur lors de la récupération des Pokémon de la génération " + generation, e);
        }
    }
    public Pokemon savePokemon(String name) {
        Pokemon pokemon = fetchPokemonData(name);
        return pokemonRepository.save(pokemon);
    }
}