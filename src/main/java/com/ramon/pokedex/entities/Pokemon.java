package com.ramon.pokedex.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Pokemon {
    @Id @GeneratedValue
    private Long id;
    private String nome;

    @ManyToOne
    private Tipo tipo;

    @ManyToOne
    private Tipo tipoSecundario;

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public Tipo getTipoSecundario() {
        return tipoSecundario;
    }

    public void setTipoSecundario(Tipo tipoSecundario) {
        this.tipoSecundario = tipoSecundario;
    }
}
