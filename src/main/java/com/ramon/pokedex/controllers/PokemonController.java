package com.ramon.pokedex.controllers;

import com.ramon.pokedex.dto.PokemonDTO;

import com.ramon.pokedex.services.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping(value = "/pokemons")
public class PokemonController {

    @Autowired
    private PokemonService service;

    @GetMapping(value = "/{id}")
    public PokemonDTO findById(@PathVariable Long id) {
        PokemonDTO dto = service.findById(id);
        return dto;
    }
}
