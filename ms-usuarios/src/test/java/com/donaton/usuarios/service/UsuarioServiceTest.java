package com.donaton.usuarios.service;

import com.donaton.usuarios.dto.LoginRequestDTO;
import com.donaton.usuarios.dto.LoginResponseDTO;
import com.donaton.usuarios.dto.UsuarioRequestDTO;
import com.donaton.usuarios.dto.UsuarioResponseDTO;
import com.donaton.usuarios.model.Usuario;
import com.donaton.usuarios.repository.UsuarioRepository;
import com.donaton.usuarios.security.JwtUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JwtUtil jwtUtil;

    @InjectMocks
    private UsuarioServiceImpl usuarioService;

    private Usuario usuario;
    private UsuarioRequestDTO requestDTO;

    @BeforeEach
    void setUp() {
        usuario = new Usuario();
        usuario.setId(1L);
        usuario.setNombre("Daniela");
        usuario.setEmail("daniela@donaton.cl");
        usuario.setPassword("encriptado");

        requestDTO = new UsuarioRequestDTO();
        requestDTO.setNombre("Daniela");
        requestDTO.setEmail("daniela@donaton.cl");
        requestDTO.setPassword("123456");
    }

    @Test
    void registrar_debeRetornarUsuarioCreado() {
        when(passwordEncoder.encode(any())).thenReturn("encriptado");
        when(usuarioRepository.save(any())).thenReturn(usuario);

        UsuarioResponseDTO resultado = usuarioService.registrar(requestDTO);

        assertNotNull(resultado);
        assertEquals("daniela@donaton.cl", resultado.getEmail());
        verify(usuarioRepository, times(1)).save(any());
    }

    @Test
    void obtenerPorId_debeRetornarUsuarioCuandoExiste() {
        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));

        UsuarioResponseDTO resultado = usuarioService.obtenerPorId(1L);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
    }

    @Test
    void obtenerPorId_debeLanzarExcepcionCuandoNoExiste() {
        when(usuarioRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(Exception.class, () -> usuarioService.obtenerPorId(99L));
    }

    @Test
    void login_debeRetornarTokenCuandoCredencialesCorrectas() {
        LoginRequestDTO loginDTO = new LoginRequestDTO();
        loginDTO.setEmail("daniela@donaton.cl");
        loginDTO.setPassword("123456");

        when(usuarioRepository.findByEmail("daniela@donaton.cl")).thenReturn(Optional.of(usuario));
        when(passwordEncoder.matches("123456", "encriptado")).thenReturn(true);
        when(jwtUtil.generarToken("daniela@donaton.cl")).thenReturn("token.jwt.falso");

        LoginResponseDTO resultado = usuarioService.login(loginDTO);

        assertNotNull(resultado);
        assertEquals("token.jwt.falso", resultado.getToken());
    }

    @Test
    void actualizar_debeRetornarUsuarioActualizado() {
        UsuarioRequestDTO dto = new UsuarioRequestDTO();
        dto.setNombre("Daniela actualizada");
        dto.setEmail("daniela@donaton.cl");
        dto.setPassword("nueva123");

        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));
        when(passwordEncoder.encode(any())).thenReturn("encriptado");
        when(usuarioRepository.save(any())).thenReturn(usuario);

        UsuarioResponseDTO resultado = usuarioService.actualizar(1L, dto);
        assertNotNull(resultado);
        verify(usuarioRepository, times(1)).save(any());
    }

    @Test
    void login_debeLanzarExcepcionCuandoPasswordIncorrecta() {
        LoginRequestDTO loginDTO = new LoginRequestDTO();
        loginDTO.setEmail("daniela@donaton.cl");
        loginDTO.setPassword("incorrecta");

        when(usuarioRepository.findByEmail("daniela@donaton.cl")).thenReturn(Optional.of(usuario));
        when(passwordEncoder.matches("incorrecta", "encriptado")).thenReturn(false);

        assertThrows(Exception.class, () -> usuarioService.login(loginDTO));
    }

    @Test
    void login_debeLanzarExcepcionCuandoUsuarioNoExiste() {
        LoginRequestDTO loginDTO = new LoginRequestDTO();
        loginDTO.setEmail("noexiste@donaton.cl");
        loginDTO.setPassword("123456");

        when(usuarioRepository.findByEmail("noexiste@donaton.cl")).thenReturn(Optional.empty());

        assertThrows(Exception.class, () -> usuarioService.login(loginDTO));
    }
}