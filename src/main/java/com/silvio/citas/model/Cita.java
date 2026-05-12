package com.silvio.citas.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
@Table(name ="citas")
public class Cita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "No debe estar en blanco")
    private Long paciente_id;
     private Long doctor_id; //nuevo dato para doctor
    private String especialidad;

    @FutureOrPresent(message = "No puede ser una fecha antes de hoy")
    private String fechaHora;
}
