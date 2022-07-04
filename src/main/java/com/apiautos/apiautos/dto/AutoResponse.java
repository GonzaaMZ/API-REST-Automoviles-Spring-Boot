package com.apiautos.apiautos.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AutoResponse {
    private List<AutoDTO> contenido;

    private int numeroPagina;

    private int medidaPagina;

    private long totalElementos;

    private int totalPaginas;

    private boolean ultima;
}
