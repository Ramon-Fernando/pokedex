package com.ramon.pokedex.services;

import com.ramon.pokedex.dto.PokemonDTO;
import com.ramon.pokedex.entities.Pokemon;
import com.ramon.pokedex.repositories.PokemonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class PokemonService {

    @Autowired
    private PokemonRepository repository;

    @Transactional(readOnly = true)
    public PokemonDTO findById(Long id){
        Optional<Pokemon> result = repository.findById(id);
        Pokemon pokemon = result.get();
        PokemonDTO dto = new PokemonDTO(pokemon);
        return dto;
    }
}
