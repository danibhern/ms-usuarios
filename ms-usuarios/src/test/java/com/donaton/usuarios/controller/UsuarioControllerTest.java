package com.donaton.usuarios.controller;

import com.donaton.usuarios.dto.LoginRequestDTO;
import com.donaton.usuarios.dto.LoginResponseDTO;
import com.donaton.usuarios.dto.UsuarioRequestDTO;
import com.donaton.usuarios.dto.UsuarioResponseDTO;
import com.donaton.usuarios.enums.RolUsuario;
import com.donaton.usuarios.security.JwtFilter;
import com.donaton.usuarios.security.JwtUtil;
import com.donaton.usuarios.service.UsuarioService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class UsuarioControllerTest {

    @TestConfiguration
    static class TestConfig {
        @Bean
        public JwtFilter jwtFilter(JwtUtil jwtUtil) {
            return new JwtFilter(jwtUtil) {
                @Override
                protected void doFilterInternal(HttpServletRequest request,
                                                HttpServletResponse response,
                                                FilterChain filterChain)
                        throws java.io.IOException, jakarta.servlet.ServletException {

                    // Simular usuario autenticado para todos los requests
                    org.springframework.security.core.Authentication auth =
                            new org.springframework.security.authentication.UsernamePasswordAuthenticationToken(
                                    "daniela@donaton.cl",
                                    null,
                                    java.util.Collections.emptyList()
                            );
                    org.springframework.security.core.context.SecurityContextHolder
                            .getContext().setAuthentication(auth);

                    filterChain.doFilter(request, response);
                }
            };
        }
    }

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private UsuarioService usuarioService;

    @MockitoBean
    private JwtUtil jwtUtil;

    private UsuarioResponseDTO responseDTO;
    private UsuarioRequestDTO requestDTO;

    @BeforeEach
    void setUp() {
        responseDTO = new UsuarioResponseDTO(
                1L,
                "Daniela",
                "daniela@donaton.cl",
                RolUsuario.DONANTE,
                LocalDateTime.now()
        );

        requestDTO = new UsuarioRequestDTO();
        requestDTO.setNombre("Daniela");
        requestDTO.setEmail("daniela@donaton.cl");
        requestDTO.setPassword("123456");
        requestDTO.setRol(RolUsuario.DONANTE); // ← agregar esta línea
    }

    @Test
    void registrar_debeRetornar201() throws Exception {
        when(usuarioService.registrar(any())).thenReturn(responseDTO);

        mockMvc.perform(post("/api/usuarios/registro")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.email").value("daniela@donaton.cl"));
    }

    @Test
    void login_debeRetornar200ConToken() throws Exception {
        LoginRequestDTO loginDTO = new LoginRequestDTO();
        loginDTO.setEmail("daniela@donaton.cl");
        loginDTO.setPassword("123456");

        LoginResponseDTO loginResponse = new LoginResponseDTO();
        loginResponse.setToken("token.jwt.falso");

        when(usuarioService.login(any())).thenReturn(loginResponse);

        mockMvc.perform(post("/api/usuarios/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").value("token.jwt.falso"));
    }

    @Test
    void obtener_debeRetornar200CuandoAutenticado() throws Exception {
        when(usuarioService.obtenerPorId(1L)).thenReturn(responseDTO);

        mockMvc.perform(get("/api/usuarios/1")
                        .header("Authorization", "Bearer token.valido"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    void actualizar_debeRetornar200() throws Exception {
        when(usuarioService.actualizar(eq(1L), any())).thenReturn(responseDTO);

        mockMvc.perform(put("/api/usuarios/1")
                        .header("Authorization", "Bearer token.valido")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Daniela"));
    }
}