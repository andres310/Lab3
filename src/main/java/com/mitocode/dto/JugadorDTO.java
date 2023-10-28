package com.mitocode.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.codehaus.jackson.annotate.JsonManagedReference;

import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class JugadorDTO {

    @EqualsAndHashCode.Include
    private Integer idJugador;
    private String nombre;
    private Integer numero;
    private Set<CamisaDTO> camisas;
    private Set<PosicionDTO> posiciones;
}
