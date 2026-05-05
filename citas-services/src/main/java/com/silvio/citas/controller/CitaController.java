package com.silvio.citas.controller;

import com.silvio.citas.model.Cita;
import com.silvio.citas.service.CitaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/citas")
@RequiredArgsConstructor
public class CitaController {

    private final CitaService citaService;

    //endpoint para crear una nueva cita
    @PostMapping("path")
    public ResponseEntity<Cita> crearCita(@RequestBody Cita cita){
        //1- llamamos al servicio (que a su vez usará feign para veriicar el paciente)
        Cita nuevaCita = citaService.crearCita(cita);

        //devolvemos la cita creada con un codigo HTTP 201 created
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaCita);
    }
    


}
