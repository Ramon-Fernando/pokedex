package com.ramon.pokedex.repositories;

import com.ramon.pokedex.entities.Tipo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipoRepository extends JpaRepository<Tipo, Long> {
}
