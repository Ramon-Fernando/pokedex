package com.ramon.pokedex.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/pokemons")
public class PokemonController {

    @GetMapping
    public String teste() {
        return "teste pokemons";
    }
}
