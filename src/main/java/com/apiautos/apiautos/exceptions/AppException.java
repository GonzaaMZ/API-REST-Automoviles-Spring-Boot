package com.apiautos.apiautos.exceptions;

import org.springframework.http.HttpStatus;

import lombok.*;

@Getter
@Setter
public class AppException extends RuntimeException {

    private static final long serialVersion = 1L;

    private HttpStatus estado;

    private String mensaje;

    public AppException(HttpStatus estado, String mensaje,String mensaje1) {
        super();
        this.estado = estado;
        this.mensaje = mensaje;
        this.mensaje = mensaje1;
    }

    public AppException(HttpStatus estado, String mensaje) {
        super();
        this.estado = estado;
        this.mensaje = mensaje;
    }

}
