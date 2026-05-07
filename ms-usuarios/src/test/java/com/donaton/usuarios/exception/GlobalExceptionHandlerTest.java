package com.donaton.usuarios.exception;

import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GlobalExceptionHandlerTest {

    private final GlobalExceptionHandler handler = new GlobalExceptionHandler();

    @Test
    void handleNotFound_debeRetornar404() {
        UsuarioNotFoundException ex = new UsuarioNotFoundException("Usuario no encontrado");

        ResponseEntity<Map<String, String>> response = handler.handleNotFound(ex);

        assertEquals(404, response.getStatusCode().value());
        assertEquals("Usuario no encontrado", response.getBody().get("error"));
    }

    @Test
    void handleRuntime_debeRetornar400() {
        RuntimeException ex = new RuntimeException("Error de negocio");

        ResponseEntity<Map<String, String>> response = handler.handleRuntime(ex);

        assertEquals(400, response.getStatusCode().value());
        assertEquals("Error de negocio", response.getBody().get("error"));
    }

    @Test
    void handleValidation_debeRetornar400ConCamposInvalidos() {
        MethodArgumentNotValidException ex = mock(MethodArgumentNotValidException.class);
        BindingResult bindingResult = mock(BindingResult.class);
        FieldError fieldError = new FieldError("dto", "nombre", "El nombre es obligatorio");

        when(ex.getBindingResult()).thenReturn(bindingResult);
        when(bindingResult.getFieldErrors()).thenReturn(List.of(fieldError));

        ResponseEntity<Map<String, String>> response = handler.handleValidation(ex);

        assertEquals(400, response.getStatusCode().value());
        assertEquals("El nombre es obligatorio", response.getBody().get("nombre"));
    }
}