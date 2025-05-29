package com.ramon.pokedex.controllers;

import com.ramon.pokedex.dto.TipoDTO;
import com.ramon.pokedex.entities.Tipo;
import com.ramon.pokedex.services.TipoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/tipos")
public class TipoController {

    @Autowired
    private TipoService service;

    @GetMapping(value = "/{id}")
    public TipoDTO findById(@PathVariable Long id) {
        TipoDTO dto = service.findById(id);
        return dto;
    }
}
