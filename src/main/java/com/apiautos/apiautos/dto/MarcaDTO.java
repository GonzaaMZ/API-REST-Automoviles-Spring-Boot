package com.apiautos.apiautos.dto;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.URL;

import com.apiautos.apiautos.models.Auto;

import lombok.*;

@Data
public class MarcaDTO {
    
    private Long id;

    @NotEmpty(message = "El nombre no puede ir vacio")
    @Size(min = 2, message = "El nombre de la marca debe tener al menos 2 caracteres")
    private String nombre;

    private Set<Auto> autos;

    @NotEmpty(message = "El pais de origen de la marca no puede ir vacio")
    private String origen;

    @URL(message = "Tiene que ser una direccion URL válida")
    private String logo;
}
