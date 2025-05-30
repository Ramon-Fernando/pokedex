package com.ramon.pokedex.services;

import com.ramon.pokedex.dto.PokemonTipoDTO;
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
public class PokemonService {

    @Autowired
    private PokemonRepository repository;

    @Autowired
    private TipoRepository tipoRepository;

    @Transactional(readOnly = true)
    public PokemonTipoDTO findById(Long id){
        Optional<Pokemon> result = repository.findById(id);
        Pokemon pokemon = result.get();
        return new PokemonTipoDTO(pokemon);
    }

    @Transactional(readOnly = true)
    public List<PokemonTipoDTO> findAll(){
        List<Pokemon> result = repository.findAll();
        return result.stream().map(x -> new PokemonTipoDTO(x)).toList();
    }


    @Transactional
    public PokemonTipoDTO insert(PokemonTipoDTO dto) {
        Pokemon entity = new Pokemon();
        dtoToEntity(dto, entity);

        entity = repository.save(entity);

        return new PokemonTipoDTO(entity);
    }

    @Transactional
    public PokemonTipoDTO update(Long id, PokemonTipoDTO dto) {
        Pokemon entity = repository.getReferenceById(id);
        dtoToEntity(dto, entity);

        entity = repository.save(entity);

        return new PokemonTipoDTO(entity);
    }

    private void dtoToEntity (PokemonTipoDTO dto, Pokemon entity) {
        entity.setNome(dto.getNome());

        Tipo tipo = tipoRepository.getReferenceById(dto.getTipo().getId());
        entity.setTipo(tipo);

        if (dto.getTipoSecundario() != null) {
            Tipo tipoSecundario = tipoRepository.getReferenceById(dto.getTipoSecundario().getId());
            entity.setTipoSecundario(tipoSecundario);
        } else {
            entity.setTipoSecundario(null);
        }
    }
}
