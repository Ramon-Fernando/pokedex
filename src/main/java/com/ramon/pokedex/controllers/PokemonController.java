package com.ramon.pokedex.controllers;

import com.ramon.pokedex.dto.PokemonDTO;

import com.ramon.pokedex.entities.Pokemon;
import com.ramon.pokedex.services.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/pokemons")
public class PokemonController {

    @Autowired
    private PokemonService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<PokemonDTO> findById(@PathVariable Long id) {
        PokemonDTO dto = service.findById(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public ResponseEntity<List<PokemonDTO>> findAll() {
        List<PokemonDTO> list = service.findAll();
        return ResponseEntity.ok(list);
    }

    @PostMapping
    public ResponseEntity<PokemonDTO> insert(@RequestBody PokemonDTO dto) {
        dto = service.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }
}
