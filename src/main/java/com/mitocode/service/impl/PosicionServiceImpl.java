package com.mitocode.service.impl;

import com.mitocode.model.Posicion;

import com.mitocode.repo.IGenericRepo;
import com.mitocode.repo.IPosicionRepo;

import com.mitocode.service.IPosicionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PosicionServiceImpl extends CRUDImpl<Posicion, Integer> implements IPosicionService {
    @Autowired
    private IPosicionRepo repo;

    @Override
    protected IGenericRepo<Posicion, Integer> getRepo() {
        return repo;
    }

    @Override
    public Posicion save(Posicion posicion) {
        if (posicion.getEstado())
            Optional.ofNullable(posicion.getJugador()).map(jugador -> {
                // Desactiva posiciones anteriores del jugador
                jugador.getPosiciones().forEach(pos -> pos.setEstado(false));
                return jugador;
            }).orElseGet(null);

        return repo.save(posicion);
    }


}
