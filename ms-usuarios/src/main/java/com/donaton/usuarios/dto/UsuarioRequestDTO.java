package com.donaton.usuarios.dto;

import com.donaton.usuarios.enums.RolUsuario;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UsuarioRequestDTO {

    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @Email(message = "El email no es válido")
    @NotBlank(message = "El email es obligatorio")
    private String email;

    @NotBlank(message = "La contraseña es obligatoria")
    private String password;

    @NotNull(message = "El rol es obligatorio")
    private RolUsuario rol;
}