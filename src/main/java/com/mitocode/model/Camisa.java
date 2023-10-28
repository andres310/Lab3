package com.mitocode.model;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Camisa {
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCamisa;
    private String tipo;
    @ManyToOne
    @JoinColumn(name = "id_jugador", referencedColumnName = "id_jugador")
    private Jugador jugador;
}
