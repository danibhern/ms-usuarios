package com.donaton.demo.Controller;

import com.donaton.demo.DTO.DonacionRequestDTO;
import com.donaton.demo.DTO.DonacionResponseDTO;
import com.donaton.demo.Model.CategoriaDonacion;
import com.donaton.demo.Model.EstadoDonacion;
import com.donaton.demo.Service.DonacionService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/donaciones")
public class DonacionController {
    private final DonacionService donacionService;
    public DonacionController(DonacionService donacionService){
        this.donacionService=donacionService;
    }

    @PostMapping
    public ResponseEntity<DonacionResponseDTO>crearDonacion(@Valid @RequestBody DonacionRequestDTO requestDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(donacionService.crearDonacion(requestDTO));
    }

    @GetMapping("/{id}")
    private ResponseEntity<DonacionResponseDTO>obtenerPorId(@PathVariable Long id){
        return ResponseEntity.ok(donacionService.obtenerPorId(id));
    }

    @GetMapping("/categoria/{cat}")
    public ResponseEntity<List<DonacionResponseDTO>>listarPorCategoria(
            @PathVariable CategoriaDonacion cat){
        return ResponseEntity.ok(donacionService.listarPorCategoria(cat));
    }
    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<DonacionResponseDTO>> listarPorEstado(
            @PathVariable EstadoDonacion estado) {
        return ResponseEntity.ok(donacionService.listarPorEstado(estado));
    }

    @GetMapping("/usuario/{donadorId}")
    public ResponseEntity<List<DonacionResponseDTO>> listarPorUsuario(
            @PathVariable Long donadorId) {
        return ResponseEntity.ok(donacionService.listarPorDonador(donadorId));
    }

}
