package com.silvio.citas.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.silvio.citas.dto.DoctorDTO;

@FeignClient(name = "doctor-service", url = "http://localhost:8083/api/doctores")
public interface DoctorClient {

    @GetMapping("/{id}")
    DoctorDTO obtenerDoctorPorId(@PathVariable("id") Long id);
}