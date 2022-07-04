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

import com.apiautos.apiautos.dto.AutoDTO;
import com.apiautos.apiautos.dto.AutoResponse;
import com.apiautos.apiautos.models.Auto;
import com.apiautos.apiautos.service.AutoService;
import com.apiautos.apiautos.utils.Constantes;

@RestController
@RequestMapping("/api/autos")
public class AutosControlller {

    @Autowired
    private AutoService autoService;

    @PostMapping
    public ResponseEntity<AutoDTO> guardarAuto(@Valid @RequestBody AutoDTO autoDTO) {
        return new ResponseEntity<AutoDTO>(autoService.crearAuto(autoDTO), HttpStatus.CREATED);
    }

    @GetMapping
    public AutoResponse obtenerAutos(@RequestParam(value = "pageNo", defaultValue = Constantes.NUMERO_PAGINA_POR_DEFECTO, required = false) int numeroPagina,
                                        @RequestParam(value = "pageSize", defaultValue = Constantes.MEDIDA_PAGINA_POR_DEFECTO, required = false) int tamañoPagina,
                                        @RequestParam(value = "ordenarPor", defaultValue = Constantes.ORDENAR_POR_DEFECTO , required = false) String ordenarPor,
                                        @RequestParam(value = "sortDIR", defaultValue = Constantes.ORDENAR_DIRECCION_POR_DEFECTO , required = false) String sortDir,
                                        @RequestParam(value = "filter", required = false) String filtro
                                        ){
        return autoService.obtenerAutos(numeroPagina, tamañoPagina, ordenarPor, sortDir, filtro);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AutoDTO> obtenerAutoById(@PathVariable(value = "id") long idAuto) {
        return new ResponseEntity<AutoDTO>(autoService.obtenerAutoById(idAuto), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AutoDTO> actualizarAuto(@Valid @RequestBody AutoDTO autoDTO,
            @PathVariable(value = "id") long idAuto) {
        return new ResponseEntity<AutoDTO>(autoService.actualizarAuto(autoDTO, idAuto), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<AutoDTO> eliminarAuto(@PathVariable(value = "id") long idAuto) {
        return new ResponseEntity<AutoDTO>(autoService.eliminarAuto(idAuto), HttpStatus.OK);
    }

}
