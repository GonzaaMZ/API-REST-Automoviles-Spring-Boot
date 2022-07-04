package com.apiautos.apiautos.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.apiautos.apiautos.dto.AutoDTO;
import com.apiautos.apiautos.dto.AutoResponse;
import com.apiautos.apiautos.dto.MarcaDTO;
import com.apiautos.apiautos.exceptions.ResourceNotFoundException;
import com.apiautos.apiautos.models.Auto;
import com.apiautos.apiautos.models.Marca;
import com.apiautos.apiautos.repository.AutoRepository;
import com.apiautos.apiautos.repository.MarcaRepository;

@Service
public class AutoServiceImpl implements AutoService{

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AutoRepository autoRepository;

    @Autowired
    private MarcaRepository marcaRepository;

    @Override
    public AutoDTO crearAuto(AutoDTO autoDTO) {
        Auto auto = mapearAEntidad(autoDTO);

        long idMarca = autoDTO.getIdMarca();

        Marca marca = marcaRepository.findById(idMarca).orElseThrow(() -> new ResourceNotFoundException("Marca", "ID", idMarca));

        auto.setMarca(marca);

        autoRepository.save(auto);

        AutoDTO autoResponse = mapearADto(auto);

        return autoResponse;
    }
    
    
    @Override
    public AutoResponse obtenerAutos(int numeroPagina, int tamañoPagina, String ordenarPor, String sortDir, String filtro) {
        
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())?Sort.by(ordenarPor).ascending():Sort.by(ordenarPor).descending();
        Pageable pageable = PageRequest.of(numeroPagina, tamañoPagina, sort);

        Page<Auto> autos;

        if(filtro != null){
            autos = autoRepository.findAll(filtro, pageable);
        }
        else {
            autos = autoRepository.findAll(pageable);
        }

        List<Auto> listaDeAutos = autos.getContent();

        List<AutoDTO> contenido = listaDeAutos.stream().map(auto -> mapearADto(auto)).collect(Collectors.toList());

        AutoResponse autoResponse = new AutoResponse();

        autoResponse.setContenido(contenido);
        autoResponse.setNumeroPagina(autos.getNumber());
        autoResponse.setMedidaPagina(autos.getSize());
        autoResponse.setTotalElementos(autos.getTotalElements());
        autoResponse.setTotalPaginas(autos.getTotalPages());
        autoResponse.setUltima(autos.isLast());

        return autoResponse;
    }

    @Override
    public AutoDTO actualizarAuto(AutoDTO autoDTO, long id) {
        Auto auto = autoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Auto", "ID", id));
        
        auto.setModelo(autoDTO.getModelo());
        auto.setImagen(autoDTO.getImagen());
        auto.setAño(autoDTO.getAño());

        autoRepository.save(auto);
        
        return mapearADto(auto);
    }

    @Override
    public AutoDTO obtenerAutoById(long id) {
        Auto auto = autoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Auto", "ID", id));
        return mapearADto(auto);
    }

    private AutoDTO mapearADto(Auto auto){
        AutoDTO autoDTO = modelMapper.map(auto, AutoDTO.class);
        return autoDTO;
    }

    private Auto mapearAEntidad(AutoDTO autoDTO){
        Auto auto = modelMapper.map(autoDTO, Auto.class);
        return auto;
    }


    @Override
    public AutoDTO eliminarAuto(long id) {
        Auto auto = autoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Auto", "ID", id));
        
        autoRepository.delete(auto);

        return mapearADto(auto);
    }






}
