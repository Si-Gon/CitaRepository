package com.silvio.citas.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.silvio.citas.dto.PacienteDTO;

@FeignClient(name = "pacientes-service", url="http://localhost:8081/api/pacientes")

public interface PacienteClient {

    @GetMapping("/{id}")
    PacienteDTO obtenerPacientePorId(@PathVariable("id") Long id);
}
