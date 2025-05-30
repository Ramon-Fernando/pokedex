package com.ramon.pokedex.repositories;

import com.ramon.pokedex.entities.Pokemon;
import com.ramon.pokedex.entities.Tipo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PokemonRepository extends JpaRepository<Pokemon, Long> {
    List<Pokemon> findByTipoOrTipoSecundario(Tipo tipo1, Tipo tipo2);
}
