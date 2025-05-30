package com.ramon.pokedex.services;

import com.ramon.pokedex.dto.PokemonDTO;
import com.ramon.pokedex.entities.Pokemon;
import com.ramon.pokedex.repositories.PokemonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PokemonService {

    @Autowired
    private PokemonRepository repository;

    @Transactional(readOnly = true)
    public PokemonDTO findById(Long id){
        Optional<Pokemon> result = repository.findById(id);
        Pokemon pokemon = result.get();
        PokemonDTO dto = new PokemonDTO(pokemon);
        return dto;
    }

    @Transactional(readOnly = true)
    public List<PokemonDTO> findAll(){
        List<Pokemon> result = repository.findAll();
        return result.stream().map(x -> new PokemonDTO(x)).toList();
    }

    @Transactional
    public PokemonDTO insert(PokemonDTO dto) {

        Pokemon entity = new Pokemon();
        entity.setNome(dto.getNome());
        entity.setTipo(dto.getTipo());
        entity.setTipoSecundario(dto.getTipoSecundario());
        entity = repository.save(entity);

        return new PokemonDTO(entity);

    }
}
