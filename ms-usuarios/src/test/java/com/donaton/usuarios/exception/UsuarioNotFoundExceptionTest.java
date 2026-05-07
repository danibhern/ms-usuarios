package com.donaton.usuarios.exception;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UsuarioNotFoundExceptionTest {

    @Test
    void excepcion_debeTenerMensajeCorrecto() {
        UsuarioNotFoundException ex = new UsuarioNotFoundException("Usuario no encontrado");
        assertEquals("Usuario no encontrado", ex.getMessage());
    }

    @Test
    void excepcion_esRuntimeException() {
        UsuarioNotFoundException ex = new UsuarioNotFoundException("error");
        assertInstanceOf(RuntimeException.class, ex);
    }
}