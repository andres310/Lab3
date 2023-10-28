package com.mitocode.model;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Posicion {
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPosicion;
    private String nombre;
    private Boolean estado;
    @ManyToOne
    @JoinColumn(name = "id_jugador", referencedColumnName = "id_jugador")
    private Jugador jugador;
}
