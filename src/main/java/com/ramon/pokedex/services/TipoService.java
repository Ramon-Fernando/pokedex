package com.ramon.pokedex.services;

import com.ramon.pokedex.dto.TipoDTO;
import com.ramon.pokedex.entities.Tipo;
import com.ramon.pokedex.repositories.TipoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class TipoService {

    @Autowired
    private TipoRepository repository;

    @Transactional(readOnly = true)
    public TipoDTO findById(Long id) {
        Optional<Tipo> result = repository.findById(id);
        Tipo tipo = result.get();
        TipoDTO dto = new TipoDTO(tipo);
        return dto;
    }

}
