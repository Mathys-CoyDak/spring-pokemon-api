CREATE TABLE pokemons (
                          id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          pokedex_id INT NOT NULL,
                          generation INT NOT NULL,
                          category VARCHAR(255) NOT NULL,
                          name_fr VARCHAR(255) NOT NULL,
                          sprite_url VARCHAR(255),
                          type VARCHAR(255),
                          hp INT NOT NULL,
                          attack INT NOT NULL,
                          defense INT NOT NULL,
                          special_attack INT NOT NULL,
                          special_defense INT NOT NULL,
                          speed INT NOT NULL
);
