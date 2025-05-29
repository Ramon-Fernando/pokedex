package com.ramon.pokedex.entities;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "tb_pokemon")
public class Pokemon {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;

    @ManyToOne
    @JoinColumn (name="tipo_id")
    private Tipo tipo;

    @ManyToOne
    @JoinColumn (name="tipo_sec_id")
    private Tipo tipoSecundario;

    public Pokemon(){
    }

    public Pokemon(String nome, Tipo tipo) {
        this.nome = nome;
        this.tipo = tipo;
    }

    public Pokemon(String nome, Tipo tipo, Tipo tipoSecundario) {
        this.nome = nome;
        this.tipo = tipo;
        this.tipoSecundario = tipoSecundario;
    }

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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Pokemon pokemon = (Pokemon) o;
        return Objects.equals(nome, pokemon.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(nome);
    }
}
