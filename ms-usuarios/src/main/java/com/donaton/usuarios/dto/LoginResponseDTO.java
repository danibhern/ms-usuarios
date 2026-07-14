package com.donaton.usuarios.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponseDTO {
    private String token;
    private String tipo = "Bearer";
    private Long usuarioId;
    private String nombre;
    private String email;

    public LoginResponseDTO(String token, Long usuarioId, String nombre, String email) {
        this.token = token;
        this.tipo = "Bearer";
        this.usuarioId = usuarioId;
        this.nombre = nombre;
        this.email = email;
    }
}