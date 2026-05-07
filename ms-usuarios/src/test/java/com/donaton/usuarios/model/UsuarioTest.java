package com.donaton.usuarios.model;

import com.donaton.usuarios.enums.RolUsuario;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UsuarioTest {

    @Test
    void usuario_debeAsignarPropiedadesCorrectamente() {
        Usuario u = new Usuario();
        u.setId(1L);
        u.setNombre("Daniela");
        u.setEmail("daniela@donaton.cl");
        u.setPassword("123456");
        u.setRol(RolUsuario.DONANTE);

        assertEquals(1L, u.getId());
        assertEquals("Daniela", u.getNombre());
        assertEquals("daniela@donaton.cl", u.getEmail());
        assertEquals("123456", u.getPassword());
        assertEquals(RolUsuario.DONANTE, u.getRol());
    }
}