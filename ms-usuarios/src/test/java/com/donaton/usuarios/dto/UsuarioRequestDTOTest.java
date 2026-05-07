package com.donaton.usuarios.dto;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UsuarioRequestDTOTest {

    @Test
    void dto_debeAsignarPropiedades() {
        UsuarioRequestDTO dto = new UsuarioRequestDTO();
        dto.setNombre("Daniela");
        dto.setEmail("daniela@donaton.cl");
        dto.setPassword("123456");

        assertEquals("Daniela", dto.getNombre());
        assertEquals("daniela@donaton.cl", dto.getEmail());
        assertEquals("123456", dto.getPassword());
    }
}