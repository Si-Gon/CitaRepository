package com.silvio.citas.service;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import com.silvio.citas.model.Cita;
import com.silvio.citas.repository.CitaRepository;
import com.silvio.citas.client.PacienteClient;
import com.silvio.citas.dto.PacienteDTO;

@Service
@RequiredArgsConstructor

public class CitaService {
    
    
    private final CitaRepository citaRepository;
    private final PacienteClient pacienteClient;


    public Cita crearCita(Cita cita){
        //usamos feign paara ir al ms de paciente y traer sus datos.citasServicesApplication
        PacienteDTO paciente = pacienteClient.obtenerPacientePorId(cita.getId());

        if (paciente == null){
            throw new RuntimeException("Paciente no encontrado");
        }
        System.out.println("Creando cita para : " + paciente.getNombre());

        //guardamos la cita en nuestra propia base de datos
        return citaRepository.save(cita);
    }
}
