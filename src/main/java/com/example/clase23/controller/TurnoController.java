package com.example.clase23.controller;

import com.example.clase23.model.Odontologo;
import com.example.clase23.model.Paciente;
import com.example.clase23.model.Turno;
import com.example.clase23.service.OdontologoService;
import com.example.clase23.service.PacienteService;
import com.example.clase23.service.TurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/turnos")
public class TurnoController {

    @Autowired
    private TurnoService turnoService;
    @Autowired
    private OdontologoService odontologoService;
    @Autowired
    private PacienteService pacienteService;


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

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarTurno (@PathVariable Integer id) {
        Turno turnoBuscado = turnoService.buscarTurno(id);
        //tratamiento
        if (turnoBuscado != null) {
            turnoService.eliminarTurno(id);
            return ResponseEntity.ok("Turno eliminado con id: " + id);
        }else{
            //build solo devolvemos el codigo bad request no el body
            return ResponseEntity.badRequest().body("Error al eliminar el turno con id: " + id);
        }
    }

    @PutMapping
    public ResponseEntity<String> actualizarTurno (@RequestBody Turno turno) {
        Turno turnoBuscado = turnoService.buscarTurno(turno.getOdontologo().getId());
        if (turnoBuscado != null) {
            if (odontologoService.buscarOdontologo(turno.getOdontologo().getId()) != null && pacienteService.buscarPaciente(turno.getPaciente().getId()) != null) {
                turnoService.actualizarTurno(turno);
                return ResponseEntity.ok("Se ha actualizado correctamente el turno" + turno.getId());
            } else {
                return ResponseEntity.badRequest().body("No se ha actualizado porque paciente o odontologo no de encuentran");
            }
        } else {
            return ResponseEntity.badRequest().body("No se actualiza, no se encuentra el turno");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Turno> buscarTurno (@PathVariable Integer id){
        Turno turnoBuscado= turnoService.buscarTurno(id);
        if(turnoBuscado != null){
            return ResponseEntity.ok(turnoBuscado);
        }else{
            return ResponseEntity.notFound().build();
        }
    }
}

















