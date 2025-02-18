CREATE TABLE IF NOT exists pokemons (
                          id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          name VARCHAR(255) NOT NULL,
                          type VARCHAR(255),
                          attack INT,
                          defense INT
);