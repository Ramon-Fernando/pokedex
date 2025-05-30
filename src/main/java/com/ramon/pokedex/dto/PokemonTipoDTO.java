package com.ramon.pokedex.dto;

import com.ramon.pokedex.entities.Pokemon;

public class PokemonTipoDTO {
    private Long id;
    private String nome;
    private TipoDTO tipo;
    private TipoDTO tipoSecundario;

    public PokemonTipoDTO() {
    }

    public PokemonTipoDTO(Long id, String nome, TipoDTO tipo, TipoDTO tipoSecundario) {
        this.id = id;
        this.nome = nome;
        this.tipo = tipo;
        this.tipoSecundario = tipoSecundario;
    }

    public PokemonTipoDTO(Pokemon pokemon) {
        id = pokemon.getId();
        nome = pokemon.getNome();
        tipo = new TipoDTO(pokemon.getTipo());
        if (pokemon.getTipoSecundario() != null) {
            tipoSecundario = new TipoDTO(pokemon.getTipoSecundario());
        }
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public TipoDTO getTipo() {
        return tipo;
    }

    public TipoDTO getTipoSecundario() {
        return tipoSecundario;
    }
}
