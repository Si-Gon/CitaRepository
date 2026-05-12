package com.silvio.citas.dto;

import lombok.Data;

@Data
public class DoctorDTO {
    private Long id;
    private String nombre;
    private String especialidad;
    private String documento;
    private String telefono;
    private String email;
}