package com.ramon.pokedex.dto;

import com.ramon.pokedex.entities.Tipo;

public class TipoDTO {
    private Long id;
    private String nome;

    public TipoDTO() {
    }

    public TipoDTO(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public TipoDTO(Tipo entity) {
        this.id = entity.getId();
        this.nome = entity.getNome();
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }
}
