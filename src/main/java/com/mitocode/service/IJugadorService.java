package com.mitocode.service;

import com.mitocode.model.Jugador;

public interface IJugadorService extends ICRUD<Jugador, Integer> {
    Jugador guardar(Jugador jugador);
    Jugador actualizar(Jugador jugador);
}
