package com.ramon.pokedex.dto;
import com.ramon.pokedex.entities.Pokemon;
import com.ramon.pokedex.entities.Tipo;


public class PokemonDTO {
    private Long id;
    private String nome;
    private Tipo tipo;
    private Tipo tipoSecundario;

    public PokemonDTO() {
    }

    public PokemonDTO(Long id, String nome, Tipo tipo, Tipo tipoSecundario) {
        this.id = id;
        this.nome = nome;
        this.tipo = tipo;
        this.tipoSecundario = tipoSecundario;
    }

    public PokemonDTO(Pokemon entity){
        id = entity.getId();
        nome = entity.getNome();
        tipo = entity.getTipo();
        tipoSecundario = entity.getTipoSecundario();
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public Tipo getTipoSecundario() {
        return tipoSecundario;
    }
}
