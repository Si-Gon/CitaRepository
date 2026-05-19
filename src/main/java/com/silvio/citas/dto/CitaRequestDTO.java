package com.silvio.citas.dto;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CitaRequestDTO {

    @NotNull(message = "El ID del paciente es obligatorio")
    private Long pacienteId;

    @NotNull(message = "El ID del doctor es obligatorio")
    private Long doctorId;

    @NotNull(message = "La especialidad es obligatoria")
    private String especialidad;

    @NotNull(message = "La fecha y hora es obligatoria")
    @FutureOrPresent(message = "La fecha y hora no puede ser en el pasado")
    private LocalDateTime fechaHora;
}