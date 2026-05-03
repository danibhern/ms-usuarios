package com.donaton.demo.DTO;

import lombok.*;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DonacionResponseDTO {
    private Long id;
    private String recurso;
    private String categoria;
    private Integer cantidad;
    private String unidad;
    private String origen;
    private String fecha;
    private String nombreCentroAcopio;
    private String estado;
}
