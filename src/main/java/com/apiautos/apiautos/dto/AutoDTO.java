package com.apiautos.apiautos.dto;

import javax.validation.constraints.*;

import org.hibernate.validator.constraints.URL;

import com.apiautos.apiautos.models.Marca;

import lombok.*;

@Data
public class AutoDTO {
    
    private Long id;

    @NotEmpty(message = "El nombre del modelo no puede ir vacio")
    @Size(min = 2, message = "El nombre del modelo debe tener mas de 2 caracteres")
    private String modelo;

    private Long idMarca;

    @URL(message = "Tiene que ser una direccion URL válida")
    private String imagen;

    @NotNull(message = "El año no puede ir vacio")
    private int año;

    private Marca marca;

}
