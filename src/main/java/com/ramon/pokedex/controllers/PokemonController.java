package com.ramon.pokedex.controllers;

import com.ramon.pokedex.dto.PokemonDTO;

import com.ramon.pokedex.services.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping
    public List<PokemonDTO> findAll() {
        return service.findAll();
    }

    @PostMapping
    public PokemonDTO insert(@RequestBody PokemonDTO dto) {
        dto = service.insert(dto);
        return dto;
    }
}
