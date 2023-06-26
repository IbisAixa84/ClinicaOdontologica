package com.example.clase23.controller;

import com.example.clase23.model.Odontologo;
import com.example.clase23.service.OdontologoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/odontologos")
public class OdontologoController {
    private OdontologoService odontologoService;

    public OdontologoController(OdontologoService odontologoService) {
        this.odontologoService = odontologoService;
    }
   @PostMapping
    public Odontologo guardarOdontologo(@RequestBody Odontologo odontologo){
        return odontologoService.guardarOdontologo(odontologo);
    }
    @GetMapping
    public ResponseEntity<List<Odontologo>> listarOdontologos(){
        return ResponseEntity.ok(odontologoService.listadoOdontologos());
    }
}
