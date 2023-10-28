package com.mitocode.service.impl;

import com.mitocode.exception.ModelNotFoundException;
import com.mitocode.model.Jugador;;
import com.mitocode.model.Posicion;
import com.mitocode.repo.IGenericRepo;
import com.mitocode.repo.IJugadorRepo;
import com.mitocode.repo.IPosicionRepo;
import com.mitocode.service.IJugadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class JugadorServiceImpl extends CRUDImpl<Jugador, Integer> implements IJugadorService {
    @Autowired
    private IJugadorRepo repo;
    @Autowired
    private IPosicionRepo posicionRepo;

    @Override
    protected IGenericRepo<Jugador, Integer> getRepo() {
        return repo;
    }

    @Override
    @Transactional
    public Jugador guardar(Jugador jugador) {
        if (jugador.getPosiciones().isEmpty())
            throw new IllegalStateException("El jugador no puede no tener posición");
        List<Posicion> posiciones = jugador.getPosiciones().isEmpty() ? new ArrayList<>() : jugador.getPosiciones().stream()
                .filter(Posicion::getEstado)
                .collect(Collectors.toList());
        if (posiciones.isEmpty())
            throw new IllegalStateException("El juagdor debe tener SOLO 1 posición activa");
        if (posiciones.size() > 1)
            throw new IllegalStateException("El juagdor solo puede jugar una posición a la vez");
        if (jugador.getCamisas().isEmpty())
            throw new IllegalStateException("El jugador debe tener al menos 1 camisa asignada");
        return repo.save(jugador);
    }

    @Override
    @Transactional
    public Jugador actualizar(Jugador jugador) {
        Integer id = Optional.ofNullable(jugador.getIdJugador())
                .orElseThrow(() -> new ModelNotFoundException("ID de jugador vacío"));
        Jugador existente = this.findById(id);

        if (existente == null)
            throw new ModelNotFoundException(String.format("No se encontró al jugador por su id: %s", id));

        if (jugador.getCamisas().isEmpty())
            throw new IllegalStateException("El jugador debe tener al menos 1 camisa asignada");

        List<Posicion> poscionesExistentes = existente.getPosiciones().stream()
                .filter(Posicion::getEstado)
                .collect(Collectors.toList());
        List<Posicion> nuevasPosiciones = jugador.getPosiciones().stream()
                .filter(Posicion::getEstado)
                .collect(Collectors.toList());

        if (poscionesExistentes.size() > 1 || nuevasPosiciones.size() > 1)
            throw new IllegalStateException("El juagdor solo puede jugar una posición a la vez");

        poscionesExistentes.forEach(posicion -> posicion.setEstado(false)); // Desactiva posiciones anteriores

        if (!poscionesExistentes.get(0).getIdPosicion().equals(nuevasPosiciones.get(0).getIdPosicion())) {
            poscionesExistentes.addAll(nuevasPosiciones); // agrega nuevas posiciones
            existente.setPosiciones(new HashSet<>(poscionesExistentes));
        }
        existente.setCamisas(jugador.getCamisas());
        existente.setNumero(jugador.getNumero());

        return repo.save(existente);
    }
}
