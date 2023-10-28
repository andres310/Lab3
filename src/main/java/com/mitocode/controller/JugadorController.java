package com.mitocode.controller;

import com.mitocode.dto.JugadorDTO;
import com.mitocode.model.Jugador;
import com.mitocode.service.IJugadorService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/jugador")
public class JugadorController {

    @Autowired
    private IJugadorService jugadorService;

    @Autowired
    private ModelMapper mapper;

    @GetMapping
    public List<JugadorDTO> getAll() {
        return jugadorService.findAll().stream()
                .map(entity -> mapper.map(entity, JugadorDTO.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public Jugador getOne(@PathVariable Integer id) {
        return jugadorService.findById(id);
    }

    @PostMapping
    public Jugador save(@RequestBody Jugador jugador) {
        return jugadorService.guardar(jugador);
    }

    @PostMapping("/{id}")
    public Jugador update(@PathVariable Integer id, @RequestBody Jugador jugador) {
        return jugadorService.actualizar(jugador);
    }
}
