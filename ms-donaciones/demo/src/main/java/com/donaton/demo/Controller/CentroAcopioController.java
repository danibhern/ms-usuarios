package com.donaton.demo.Controller;

import com.donaton.demo.DTO.AcopioRequestDTO;
import com.donaton.demo.DTO.AcopioResponseDTO;
import com.donaton.demo.Service.CentroAcopioService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/centros")
public class CentroAcopioController {

    private final CentroAcopioService centroAcopioService;

    public CentroAcopioController(CentroAcopioService centroAcopioService){
        this.centroAcopioService =centroAcopioService;
    }

    @PostMapping
    public ResponseEntity<AcopioResponseDTO> crear(@Valid @RequestBody AcopioRequestDTO request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(centroAcopioService.crear(request));
    }

    @GetMapping
    public ResponseEntity<List<AcopioResponseDTO>> listar() {
        return ResponseEntity.ok(centroAcopioService.listar());
    }

    @GetMapping("/{id}")
    private ResponseEntity<AcopioResponseDTO>obtenerPorId(@PathVariable Long id){
        return ResponseEntity.ok(centroAcopioService.obtenerPorId(id));
    }

    @GetMapping("/region/{region}")
    public ResponseEntity<List<AcopioResponseDTO>>listarPorRegion(@PathVariable String region){
        return ResponseEntity.ok(centroAcopioService.listarPorRegion(region));

    }

    @GetMapping("/activos")
    public ResponseEntity<List<AcopioResponseDTO>>listarActivos(){
        return ResponseEntity.ok(centroAcopioService.listarActivo());
    }



}
