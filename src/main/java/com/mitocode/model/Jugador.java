package com.mitocode.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Jugador {
    @EqualsAndHashCode.Include
    @Id
    @Column(name = "id_jugador")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idJugador;
    private String nombre;
    private Integer numero;
    @OneToMany(mappedBy = "jugador", fetch = FetchType.EAGER)
    private Set<Camisa> camisas;
    @OneToMany(mappedBy = "jugador", fetch = FetchType.EAGER)
    private Set<Posicion> posiciones; // solo puede haber una activa
}
