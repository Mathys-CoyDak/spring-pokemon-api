package com.security.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
public class PokemonDTO {
    private int pokedex_id;
    private int generation;
    private String category;
    private Map<String, String> name; // "fr", "en", "jp"
    private Sprites sprites;
    private List<Type> types;
    private Stats stats;

    @Getter
    @Setter
    public static class Sprites {
        private String regular;
    }

    @Getter
    @Setter
    public static class Type {
        private String name;
    }

    @Getter
    @Setter
    public static class Stats {
        private int hp;
        private int atk;
        private int def;
        private int spe_atk;
        private int spe_def;
        private int vit;
    }
}
