package com.digital.ClinicaOdontologica.controller;
import com.digital.ClinicaOdontologica.dto.TurnoDto;
import com.digital.ClinicaOdontologica.entities.Odontologo;
import com.digital.ClinicaOdontologica.entities.Paciente;
import com.digital.ClinicaOdontologica.entities.Turno;
import com.digital.ClinicaOdontologica.exception.BadRequestException;
import com.digital.ClinicaOdontologica.exception.ResourceNotFoundException;
import com.digital.ClinicaOdontologica.service.OdontologoService;
import com.digital.ClinicaOdontologica.service.PacienteService;
import com.digital.ClinicaOdontologica.service.TurnoService;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.Priority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/turnos")
public class TurnoController {
    private TurnoService turnoService;
    private OdontologoService odontologoService;
    private PacienteService pacienteService;

    Logger logger = Logger.getLogger(TurnoController.class);

    @Autowired
    public TurnoController(TurnoService turnoService, OdontologoService odontologoService, PacienteService pacienteService) {
        this.turnoService = turnoService;
        this.odontologoService = odontologoService;
        this.pacienteService = pacienteService;
    }


    @PostMapping
    public ResponseEntity<TurnoDto> registrarTurno(@RequestBody Turno turno) throws BadRequestException {
       //ResponseEntity<TurnoDto> response;
        // Buscar el paciente por su ID
        Optional<Paciente> pacienteBuscado = pacienteService.buscarPacientePorID(turno.getPaciente().getId());
        // Buscar el odontólogo por su ID
        Optional<Odontologo> odontologoBuscado = odontologoService.buscarOdontologoPorId(turno.getOdontologo().getId());

        if (pacienteBuscado.isPresent() && odontologoBuscado.isPresent()) {
            // Ambos, el paciente y el odontólogo existen
            logger.log(Level.INFO,"Turno creado exitosamente");
            return ResponseEntity.ok(turnoService.guardarTurno(turno));
            //TurnoDto turnoGuardado = turnoService.guardarTurno(turno);
            //response = ResponseEntity.ok(turnoGuardado);
        } else {
            // El paciente o el odontólogo no existen
            //response = ResponseEntity.badRequest().build();
            throw new BadRequestException("El paciente o el odontologo no existen");
        }
        //return response;
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarTurno (@PathVariable Long id) throws ResourceNotFoundException {
        Optional<TurnoDto> turnoBuscado =turnoService.buscarTurnoPorID(id);
        //tratamiento
       if (turnoBuscado.isPresent()){
           turnoService.eliminarTurno(id);
           logger.log(Level.INFO,"El turno con id= : " +id+ " se elimino correctamente");
            return ResponseEntity.ok("El turno con id= : " +id+ " se elimino correctamente");

        }else{
            //build solo devolvemos el codigo bad request no el body
           throw new ResourceNotFoundException("Error al eliminar el turno con id: "+id);
         //return ResponseEntity.badRequest().body("Error al eliminar el turno con id: "+id);
        }

    }

   @PutMapping
    public ResponseEntity<String> actualizarTurno (@RequestBody Turno turno)throws BadRequestException{
      Optional<TurnoDto> turnoBuscado= turnoService.buscarTurnoPorID(turno.getId());
      if(turnoBuscado.isPresent()){
          if(odontologoService.buscarOdontologoPorId(turno.getOdontologo().getId()).isPresent() && pacienteService.buscarPacientePorID(turno.getPaciente().getId()).isPresent()){
            turnoService.actualizarTurno(turno);
            logger.log(Level.INFO,"Se ha actualizado correctamente el turno con id= "+ turno.getId());
            return ResponseEntity.ok("Se ha actualizado correctamente el turno con id= "+ turno.getId());
        }else{
              throw new BadRequestException("No se ha actualizo porque paciente u odontologo no se encuentran");
               //return ResponseEntity.badRequest().body("No se ha actualizo porque paciente o odontologo no se encuentran");
           }
       }else{
            throw new BadRequestException("No se actuliza, no se encuentra el turno");
           //return ResponseEntity.badRequest().body("No se actuliza, no se encuentra el turno");
        }
    }



    @GetMapping("/{id}")
    public ResponseEntity<TurnoDto> buscarTurnoPorId (@PathVariable Long id)throws ResourceNotFoundException{
       Optional <TurnoDto> turnoBuscado= turnoService.buscarTurnoPorID(id);
        if(turnoBuscado.isPresent()){
            logger.log(Level.INFO,"El turno con el id= "+ id +" fue encontrado en la base de datos");
            return ResponseEntity.ok(turnoBuscado.get());
        }else{
            throw new ResourceNotFoundException("Turno con id: "+id+" no se encuetra en la base de datos");
            //return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/listar")
    public ResponseEntity<List<TurnoDto>> traerTodosLosTurnos() {
        logger.log(Level.INFO,"Listando turnos...");
        return ResponseEntity.ok(turnoService.listarTurnos());
    }

}
