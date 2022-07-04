package com.apiautos.apiautos.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apiautos.apiautos.dto.MarcaDTO;
import com.apiautos.apiautos.exceptions.ResourceNotFoundException;
import com.apiautos.apiautos.models.Auto;
import com.apiautos.apiautos.models.Marca;
import com.apiautos.apiautos.repository.MarcaRepository;

@Service
public class MarcaServiceImpl implements MarcaService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private MarcaRepository marcaRepository;

    @Override
    public MarcaDTO crearMarca(MarcaDTO marcaDTO) {
        Marca marca = mapearAEntidad(marcaDTO);

        marcaRepository.save(marca);

        MarcaDTO marcaResponse = mapearADto(marca);
        return marcaResponse;
    }

    @Override
    public List<MarcaDTO> obtenerMarcas() {
        List<Marca> marcas = marcaRepository.findAll();

        List<MarcaDTO> listadoMarcasResponse = marcas.stream().map(marca -> mapearADto(marca)).collect(Collectors.toList());

        return listadoMarcasResponse;
    }

    @Override
    public MarcaDTO obtenerMarcaById(long id) {
        Marca marca = marcaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Marca", "ID", id));

        return mapearADto(marca);
    }

    @Override
    public MarcaDTO actualizarMarca(MarcaDTO marcaDTO, long id) {
        Marca marca = marcaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Marca", "ID", id));

        marca.setNombre(marcaDTO.getNombre());
        marca.setLogo(marcaDTO.getLogo());
        marca.setOrigen(marcaDTO.getOrigen());

        marcaRepository.save(marca);

        return mapearADto(marca);
    }

    @Override
    public MarcaDTO eliminarMarca(long id) {
        Marca marca = marcaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Marca", "ID", id));
        
        for (Auto auto : marca.getAutos()) {
            marca.removeAuto(auto);
        }

        marcaRepository.delete(marca);

        return mapearADto(marca);
    
    }

    private MarcaDTO mapearADto(Marca marca){
        MarcaDTO marcaDTO = modelMapper.map(marca, MarcaDTO.class);
        return marcaDTO;
    }

    private Marca mapearAEntidad(MarcaDTO marcaDTO){
        Marca marca = modelMapper.map(marcaDTO, Marca.class);
        return marca;
    }




}
