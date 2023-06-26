package com.example.clase23.controller;

import com.example.clase23.model.Odontologo;
import com.example.clase23.model.Paciente;
import com.example.clase23.model.Turno;
import com.example.clase23.service.OdontologoService;
import com.example.clase23.service.PacienteService;
import com.example.clase23.service.TurnoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/turnos")
public class TurnoController {
    private TurnoService turnoService;
    private OdontologoService odontologoService;
    private PacienteService pacienteService;

    public TurnoController(TurnoService turnoService, OdontologoService odontologoService, PacienteService pacienteService) {
        this.turnoService = turnoService;
        this.odontologoService = odontologoService;
        this.pacienteService = pacienteService;
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Turno>> traerTurnos(){
        return ResponseEntity.ok(turnoService.listarTurnos());
    }

    @PostMapping
    public ResponseEntity<Turno> registrarturno(@RequestBody Turno turno){
        ResponseEntity<Turno> response;
        //tratamiento
        Paciente pacienteBuscado= pacienteService.buscarPaciente(turno.getPaciente().getId());
        Odontologo odontologoBuscado= odontologoService.buscarOdontologo(turno.getOdontologo().getId());
        if (pacienteBuscado != null && odontologoBuscado != null){
            response= ResponseEntity.ok(turnoService.guardarTurno(turno));
        }else{
            //build solo devolvemos el codigo bad request no el body
            response= ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return response;
    }

    /*@DeleteMapping("eliminar/{id}")
    public ResponseEntity<Turno> eliminarTurno (@PathVariable Integer id){
        Turno turnoBuscado= turnoService.buscarTurno(id);
        ResponseEntity<Turno> response;
        if (turnoBuscado != null){
            response= ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return response;
    }*/

    @PutMapping("/actualizar")
    public ResponseEntity<Turno> actualizarTurno (@PathVariable Integer id){
        return null;
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<Turno> buscarTurnoPorId (@PathVariable Integer id){
        return null;
    }
}

















