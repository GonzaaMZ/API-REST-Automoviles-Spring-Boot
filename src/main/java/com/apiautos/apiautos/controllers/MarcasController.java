package com.apiautos.apiautos.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.apiautos.apiautos.dto.MarcaDTO;
import com.apiautos.apiautos.models.Marca;
import com.apiautos.apiautos.service.MarcaService;

@RestController
@RequestMapping("/api/marcas")
public class MarcasController {
    
    @Autowired
    private MarcaService marcaService;

    @PostMapping
    public ResponseEntity<MarcaDTO> guardarMarca(@Valid @RequestBody MarcaDTO marcaDTO){
        return new ResponseEntity<>(marcaService.crearMarca(marcaDTO), HttpStatus.CREATED);
    }

    @GetMapping
    public List<MarcaDTO> obtenerMarcas(){
        return marcaService.obtenerMarcas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MarcaDTO> obtenerMarcaById(@PathVariable(value = "id") long idMarca){
        return new ResponseEntity<>(marcaService.obtenerMarcaById(idMarca), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MarcaDTO> actualizarMarca(@Valid @RequestBody MarcaDTO marcaDTO, @PathVariable(value = "id") long idMarca){
        return new ResponseEntity<>(marcaService.actualizarMarca(marcaDTO, idMarca), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MarcaDTO> eliminarMarca(@PathVariable(value = "id") long idMarca){
        return new ResponseEntity<>(marcaService.eliminarMarca(idMarca), HttpStatus.OK);
    }
}
