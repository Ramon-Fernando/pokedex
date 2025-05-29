package com.ramon.pokedex.repositories;

import com.ramon.pokedex.entities.Pokemon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PokemonRepository extends JpaRepository<Pokemon, Long> {
}
