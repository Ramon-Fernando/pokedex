package com.ramon.pokedex.entities;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "tb_tipo")
public class Tipo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;

    public Tipo(){
    }

    public Tipo(String nome) {
        this.nome = nome;
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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Tipo tipo = (Tipo) o;
        return Objects.equals(nome, tipo.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(nome);
    }
}


