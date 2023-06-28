package com.example.clase23.controller;

import com.example.clase23.entities.Turno;
import com.example.clase23.service.OdontologoService;
import com.example.clase23.service.PacienteService;
import com.example.clase23.service.TurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/turnos")
public class TurnoController {
    @Autowired
    private TurnoService turnoService;
    @Autowired
    private PacienteService pacienteService;
    @Autowired
    private OdontologoService odontologoService;
    @PostMapping
    public ResponseEntity<Turno> guardarTurno(@RequestBody Turno turno){
        if(odontologoService.buscarOdontologo(turno.getOdontologo().getId()).isPresent()&&pacienteService.buscarPacientePorID(turno.getPaciente().getId()).isPresent()){
            return ResponseEntity.ok(turnoService.guardarTurno(turno));
        }else {
            return ResponseEntity.badRequest().build();
        }
    }
    @GetMapping
    public ResponseEntity<List<Turno>> listarTurnos(){
        return ResponseEntity.ok(turnoService.listarTurnos());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Turno> buscarPorId(@PathVariable Long id){
        Optional<Turno> turnoBuscado= turnoService.buscarPorID(id);
        if(turnoBuscado.isPresent()){
            return ResponseEntity.ok(turnoBuscado.get());
        }else{
            return ResponseEntity.notFound().build();
        }

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarTurno(@PathVariable Long id){
        Optional<Turno> turnoBuscado= turnoService.buscarPorID(id);
        if(turnoBuscado.isPresent()){
            turnoService.eliminarTurno(id);
            return ResponseEntity.ok("Se elimino correctamente el turno solicitado con ID: "+id);
        }else{
            return ResponseEntity.badRequest().body("No existe el turno con ID: "+id);
        }
    }
    @PutMapping
    public ResponseEntity<String> actualizarTurno(@RequestBody Turno turno){
        Optional<Turno> turnoBuscado= turnoService.buscarPorID(turno.getId());
        if(turnoBuscado.isPresent()){
            turnoService.actualizarTurno(turno);
            return ResponseEntity.ok("Se actualizo correctamente el turno solicitado con ID: "+turno.getId());
        }else{
            return ResponseEntity.badRequest().body("No existe o no se puede actualizar el turno con ID: "+turno.getId());
        }
    }


}
