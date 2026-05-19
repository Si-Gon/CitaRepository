package com.silvio.citas.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.silvio.citas.dto.CitaRequestDTO;
import com.silvio.citas.dto.CitaResponseDTO;
import com.silvio.citas.model.Cita;
import com.silvio.citas.repository.CitaRepository;
import com.silvio.citas.client.PacienteClient;
import com.silvio.citas.client.DoctorClient;
import com.silvio.citas.dto.PacienteDTO;
import com.silvio.citas.dto.DoctorDTO;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CitaService {

    private final CitaRepository citaRepository;
    private final PacienteClient pacienteClient;
    private final DoctorClient doctorClient;   

    public CitaResponseDTO crearCita(CitaRequestDTO request) {

        // --- Paso 1: Verificar que el paciente existe ---
        // Si el paciente no existe, PacienteClient lanzará una FeignException.
        // La capturamos y convertimos en un mensaje claro.
        PacienteDTO paciente;
        try {
            paciente = pacienteClient.obtenerPacientePorId(request.getPacienteId());
        } catch (Exception e) {
            throw new RuntimeException("Paciente no encontrado con id: " + request.getPacienteId());
        }

        // --- Paso 2: Verificar que el doctor existe ---
        // Ahora validamos igual que el paciente.
        DoctorDTO doctor;
        try {
            doctor = doctorClient.obtenerDoctorPorId(request.getDoctorId());
        } catch (Exception e) {
            throw new RuntimeException("Doctor no encontrado con id: " + request.getDoctorId());
        }

        // --- Paso 3: Construir la entidad desde el RequestDTO ---
        // Aquí hacemos la conversión DTO → Entidad manualmente.
        Cita cita = new Cita();
        cita.setPacienteId(request.getPacienteId());
        cita.setDoctorId(request.getDoctorId());
        cita.setEspecialidad(request.getEspecialidad());
        cita.setFechaHora(request.getFechaHora());

        // --- Paso 4: Guardar en BD ---
        Cita citaGuardada = citaRepository.save(cita);

        // --- Paso 5: Construir respuesta enriquecida ---
        return mapearADto(citaGuardada, paciente, doctor);
    }

    public List<CitaResponseDTO> obtenerTodas() {
    return citaRepository.findByActivaTrue()
            .stream()
            .map(cita -> {
                PacienteDTO paciente = pacienteClient.obtenerPacientePorId(cita.getPacienteId());
                DoctorDTO doctor = doctorClient.obtenerDoctorPorId(cita.getDoctorId());
                return mapearADto(cita, paciente, doctor);
            })
            .collect(Collectors.toList());
}

    public CitaResponseDTO obtenerPorId(Long id) {
        Cita cita = citaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cita no encontrada con id: " + id));

        PacienteDTO paciente = pacienteClient.obtenerPacientePorId(cita.getPacienteId());
        DoctorDTO doctor = doctorClient.obtenerDoctorPorId(cita.getDoctorId());

        return mapearADto(cita, paciente, doctor);
    }

    // ---------------------------------------------------------------------------
    // ELIMINAR CITA
    // ---------------------------------------------------------------------------
    public CitaResponseDTO cancelarCita(Long id) {
    Cita cita = citaRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Cita no encontrada con id: " + id));

    cita.setActiva(false);  // marcar como cancelada, no borrar
    Cita citaActualizada = citaRepository.save(cita);

    PacienteDTO paciente = pacienteClient.obtenerPacientePorId(cita.getPacienteId());
    DoctorDTO doctor = doctorClient.obtenerDoctorPorId(cita.getDoctorId());

    return mapearADto(citaActualizada, paciente, doctor);
}

    private CitaResponseDTO mapearADto(Cita cita, PacienteDTO paciente, DoctorDTO doctor) {
        CitaResponseDTO dto = new CitaResponseDTO();
        dto.setId(cita.getId());
        dto.setPacienteId(cita.getPacienteId());
        dto.setDoctorId(cita.getDoctorId());
        dto.setEspecialidad(cita.getEspecialidad());
        dto.setFechaHora(cita.getFechaHora());
        dto.setActiva(cita.isActiva());

        // Datos que vienen de otros microservicios — enriquecen la respuesta
        dto.setNombrePaciente(paciente.getNombre());
        dto.setNombreDoctor(doctor.getNombre());
        return dto;
    }
}
