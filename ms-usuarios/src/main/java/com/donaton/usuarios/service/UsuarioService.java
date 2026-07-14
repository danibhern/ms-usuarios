package com.donaton.usuarios.service;

import com.donaton.usuarios.dto.LoginRequestDTO;
import com.donaton.usuarios.dto.LoginResponseDTO;
import com.donaton.usuarios.dto.UsuarioRequestDTO;
import com.donaton.usuarios.dto.UsuarioResponseDTO;

public interface UsuarioService {
    UsuarioResponseDTO registrar(UsuarioRequestDTO dto);
    UsuarioResponseDTO obtenerPorId(Long id);
    UsuarioResponseDTO actualizar(Long id, UsuarioRequestDTO dto);
    LoginResponseDTO login(LoginRequestDTO dto);
}
