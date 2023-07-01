package com.digital.ClinicaOdontologica.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class OdontologoDto {

    private Long id;

    private String matricula;

    private String nombre;

    private String apellido;

    public OdontologoDto() {
    }

    public OdontologoDto(Long id, String matricula, String nombre, String apellido) {
        this.id = id;
        this.matricula = matricula;
        this.nombre = nombre;
        this.apellido = apellido;
    }
}
