package com.ramon.pokedex.controllers;

import com.ramon.pokedex.dto.PokemonTipoDTO;
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
    public ResponseEntity<PokemonTipoDTO> findById(@PathVariable Long id) {
        PokemonTipoDTO dto = service.findById(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public ResponseEntity<List<PokemonTipoDTO>> findAll() {
        List<PokemonTipoDTO> list = service.findAll();
        return ResponseEntity.ok(list);
    }

    @PostMapping
    public ResponseEntity<PokemonTipoDTO> insert(@RequestBody PokemonTipoDTO dto) {
        dto = service.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }
}
