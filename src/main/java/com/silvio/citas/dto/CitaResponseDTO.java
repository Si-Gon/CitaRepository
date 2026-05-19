package com.silvio.citas.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CitaResponseDTO {

    private Long id;

    private Long pacienteId;
    private Long doctorId;

    private String nombrePaciente;
    private String nombreDoctor;

    private String especialidad;
    private LocalDateTime fechaHora;

    private boolean activa;
}