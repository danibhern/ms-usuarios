package com.donaton.usuarios.security;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.*;

class JwtUtilTest {

    private JwtUtil jwtUtil;

    @BeforeEach
    void setUp() {
        jwtUtil = new JwtUtil();
        ReflectionTestUtils.setField(jwtUtil, "secret", "donaton-clave-secreta-super-segura-2026-minimo-32-caracteres");
        ReflectionTestUtils.setField(jwtUtil, "expiration", 86400000L);
    }

    @Test
    void generarToken_debeRetornarTokenNoNulo() {
        String token = jwtUtil.generarToken("daniela@donaton.cl");
        assertNotNull(token);
        assertFalse(token.isEmpty());
    }

    @Test
    void validarToken_debeRetornarTrueCuandoTokenValido() {
        String token = jwtUtil.generarToken("daniela@donaton.cl");
        assertTrue(jwtUtil.validarToken(token));
    }

    @Test
    void validarToken_debeRetornarFalseCuandoTokenInvalido() {
        assertFalse(jwtUtil.validarToken("token.invalido.falso"));
    }

    @Test
    void obtenerEmail_debeRetornarEmailCorrecto() {
        String token = jwtUtil.generarToken("daniela@donaton.cl");
        assertEquals("daniela@donaton.cl", jwtUtil.obtenerEmail(token));
    }

    @Test
    void validarToken_debeRetornarFalseCuandoTokenExpirado() {
        ReflectionTestUtils.setField(jwtUtil, "expiration", -1000L);
        String token = jwtUtil.generarToken("daniela@donaton.cl");
        assertFalse(jwtUtil.validarToken(token));
    }
}