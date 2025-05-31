package com.ramon.pokedex.services;

import com.ramon.pokedex.dto.TipoDTO;
import com.ramon.pokedex.entities.Pokemon;
import com.ramon.pokedex.entities.Tipo;
import com.ramon.pokedex.repositories.PokemonRepository;
import com.ramon.pokedex.repositories.TipoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class TipoService {

    @Autowired
    private TipoRepository repository;
    @Autowired
    private PokemonRepository pokemonRepository;

    @Transactional(readOnly = true)
    public TipoDTO findById(Long id) {
        Optional<Tipo> result = repository.findById(id);
        Tipo tipo = result.get();
        TipoDTO dto = new TipoDTO(tipo);
        return dto;
    }

    @Transactional(readOnly = true)
    public List<TipoDTO> findAll() {
        List<Tipo> result = repository.findAll();
        return result.stream().map(x -> new TipoDTO(x)).toList();
    }

    @Transactional
    public TipoDTO insert(TipoDTO dto) {
        Tipo entity = new Tipo();
        entity.setNome(dto.getNome());
        entity = repository.save(entity);

        return new TipoDTO(entity);
    }

    @Transactional
    public TipoDTO update(Long id, TipoDTO dto) {
        Tipo entity = repository.getReferenceById(id);
        entity.setNome(dto.getNome());
        entity = repository.save(entity);

        return new TipoDTO(entity);
    }

    @Transactional
    public void delete(Long id) {
        Tipo tipo = repository.findById(id).get();
        List<Pokemon> pokemonList = pokemonRepository.findByTipoOrTipoSecundario(tipo, tipo);
        for (Pokemon pokemon : pokemonList) {
            if (pokemon.getTipo().equals(tipo)) {
                pokemon.setTipo(null);
            }
            if (pokemon.getTipoSecundario() != null && pokemon.getTipoSecundario().equals(tipo)) {
                pokemon.setTipoSecundario(null);
            }
        }
        pokemonRepository.saveAll(pokemonList);
        repository.deleteById(id);
    }
}
