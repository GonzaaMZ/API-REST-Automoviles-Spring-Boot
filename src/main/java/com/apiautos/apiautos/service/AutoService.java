package com.apiautos.apiautos.service;

import java.util.List;

import com.apiautos.apiautos.dto.AutoDTO;
import com.apiautos.apiautos.dto.AutoResponse;

public interface AutoService {

    public AutoDTO crearAuto(AutoDTO autoDTO);

    public AutoResponse obtenerAutos(int numeroPagina, int tamañoPagina, String ordenarPor, String sortDir, String filtro);

    public AutoDTO obtenerAutoById(long id);
    
    public AutoDTO actualizarAuto(AutoDTO autoDTO, long id);

    public AutoDTO eliminarAuto(long id);
}
