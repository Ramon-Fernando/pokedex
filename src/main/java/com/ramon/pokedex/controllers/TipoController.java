package com.ramon.pokedex.controllers;

import com.ramon.pokedex.dto.TipoDTO;
import com.ramon.pokedex.services.TipoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping
    public List<TipoDTO> findAll() {
        return service.findAll();
    }

    @PostMapping
    public TipoDTO insert(@RequestBody TipoDTO dto) {
        return service.insert(dto);
    }
}
