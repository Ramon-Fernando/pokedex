package com.ramon.pokedex.controllers;

import com.ramon.pokedex.entities.Pokemon;
import com.ramon.pokedex.repositories.PokemonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping(value = "/pokemons")
public class PokemonController {

    @Autowired
    private PokemonRepository repository;

    @GetMapping
    public String teste() {
        Optional<Pokemon> result = repository.findById(1L);
        Pokemon pokemon = result.get();
        return pokemon.getNome();
    }
}
