package com.silvio.citas.controller;

import com.silvio.citas.dto.CitaRequestDTO;
import com.silvio.citas.dto.CitaResponseDTO;
import com.silvio.citas.service.CitaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//   POST   /api/citas        → crear
//   GET    /api/citas        → listar todos
//   GET    /api/citas/{id}   → obtener uno
//   PATCH /api/citas/{id}   → cancelar

//   201 CREATED   → POST exitoso
//   200 OK  → PATCH /cancelar exitoso


@RestController
@RequestMapping("/api/citas")
@RequiredArgsConstructor
public class CitaController {

    private final CitaService citaService;

    // POST /api/citas

    @PostMapping
    public ResponseEntity<CitaResponseDTO> crearCita(@Valid @RequestBody CitaRequestDTO request) {
        CitaResponseDTO nuevaCita = citaService.crearCita(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaCita);
    }

    @GetMapping
    public ResponseEntity<List<CitaResponseDTO>> obtenerTodas() {
        List<CitaResponseDTO> citas = citaService.obtenerTodas();
        return ResponseEntity.ok(citas);
    }

    // GET /api/citas/{id}
    @GetMapping("/{id}")
    public ResponseEntity<CitaResponseDTO> obtenerPorId(@PathVariable Long id) {
        CitaResponseDTO cita = citaService.obtenerPorId(id);
        return ResponseEntity.ok(cita);
    }

    // PATCH /api/citas/{id}/cancelar
    
    @PatchMapping("/{id}/cancelar")
public ResponseEntity<CitaResponseDTO> cancelarCita(@PathVariable Long id) {
    CitaResponseDTO cita = citaService.cancelarCita(id);
    return ResponseEntity.ok(cita);
}
}
