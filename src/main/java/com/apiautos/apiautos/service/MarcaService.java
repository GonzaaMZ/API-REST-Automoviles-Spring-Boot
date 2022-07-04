package com.apiautos.apiautos.service;

import java.util.List;

import com.apiautos.apiautos.dto.MarcaDTO;


public interface MarcaService {
    
    public MarcaDTO crearMarca(MarcaDTO marcaDTO);

    public List<MarcaDTO> obtenerMarcas(); 

    public MarcaDTO obtenerMarcaById(long id);

    public MarcaDTO actualizarMarca(MarcaDTO marcaDTO, long id);

    public MarcaDTO eliminarMarca(long id);
}
