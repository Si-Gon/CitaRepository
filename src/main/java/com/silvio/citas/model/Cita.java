package com.silvio.citas.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "citas")
public class Cita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "El ID del paciente es obligatorio")
    @Column(name = "paciente_id", nullable = false)
    private Long pacienteId;

    @NotNull(message = "El ID del doctor es obligatorio")
    @Column(name = "doctor_id", nullable = false)
    private Long doctorId;

    @Column(nullable = false, length = 100)
    private String especialidad;

    @FutureOrPresent(message = "La fecha y hora no puede ser en el pasado")
    @Column(name = "fecha_hora", nullable = false)
    private LocalDateTime fechaHora;

    @Column(nullable = false)
    private boolean activa = true;  
}

