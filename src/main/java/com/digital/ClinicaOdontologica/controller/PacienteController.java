package com.digital.ClinicaOdontologica.controller;

import com.digital.ClinicaOdontologica.entities.Paciente;
import com.digital.ClinicaOdontologica.exception.BadRequestException;
import com.digital.ClinicaOdontologica.exception.ResourceNotFoundException;
import com.digital.ClinicaOdontologica.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    private PacienteService pacienteService;
    @Autowired
    public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    //---------------------POST GUARDAR PACIENTE --------------------------------
    @PostMapping
    public ResponseEntity<Paciente> guardarPaciente(@RequestBody Paciente paciente) {
        return ResponseEntity.ok(pacienteService.guardarPaciente(paciente));
    }

    //----------------------GET LISTAR PACIENTE ------------------------
    @GetMapping("/listar")
    public ResponseEntity<List<Paciente>> listarPacientes() throws BadRequestException {
        List<Paciente> pacientes = pacienteService.devolverPacientes();
        if(pacientes.isEmpty()){ //inEmpty verifica si la lista esta vacia
            //return ResponseEntity.badRequest().build();
            throw new BadRequestException("La lista se encuentra vacia");
        }else{
            return ResponseEntity.ok(pacientes);
        }
    }

    //----------------------DELETE PACIENTE SE AGREGA E------------------------

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarPaciente(@PathVariable Long id)throws ResourceNotFoundException {
        Optional<Paciente> pacienteBuscado = pacienteService.buscarPacientePorID(id);
        if(pacienteBuscado.isPresent()){
            pacienteService.eliminarPaciente(id);
            return ResponseEntity.ok("Se elimino correctamente el paciente con id: "+ id);
        }else {
            //no existe para eliminar el paciente
            //arrojamos la exception
            throw new ResourceNotFoundException("Error al eliminar el id: "+id+" no se encontró.");
        }
    }

    //----------------------GET BUSCAR POR ID ------------------------
    @GetMapping("/{id}")
    public ResponseEntity<Paciente> buscarPacientePorId(@PathVariable Long id)throws ResourceNotFoundException{
        Optional<Paciente> pacienteBuscado= pacienteService.buscarPacientePorID(id);
        if(pacienteBuscado.isPresent()){
            return ResponseEntity.ok(pacienteBuscado.get());
        }else {
            //return ResponseEntity.notFound().build();
            throw new ResourceNotFoundException("Error al buscar id: "+id+" no se encontró.");
        }
    }

    //----------------------ACTUALIZAR PACIENTE ------------------------
    @PutMapping
    public ResponseEntity<String> actualizarPaciente(@RequestBody Paciente paciente)throws BadRequestException{
        Optional<Paciente> pacienteBuscado = pacienteService.buscarPacientePorID(paciente.getId());
        if(pacienteBuscado.isPresent()){
            pacienteService.actualizarPaciente(paciente);
            return ResponseEntity.ok("Se actualizo correctamente el paciente con id: "+paciente.getId()+" Nombre: "+paciente.getNombre());
        }else{
            throw new BadRequestException("El paciente con id: "+paciente.getId()+" no se encuentra.");
            //return ResponseEntity.badRequest().body("El paciente con id: "+paciente.getId()+" no se encuentra.");
        }
    }
    //----------------------GET BUSCAR POR EMAIL ------------------------
    @GetMapping("/email/{email}")
    public ResponseEntity<Paciente> buscarPacientePorEmail(@PathVariable String email)throws ResourceNotFoundException{
        Optional<Paciente> pacienteBuscado= pacienteService.buscarPacientePorCorreo(email);
        if(pacienteBuscado.isPresent()){
            return ResponseEntity.ok(pacienteBuscado.get());
        }else {
            throw new ResourceNotFoundException("El email ingresado no se encuentra.");
            //return ResponseEntity.notFound().build(); //NOTFOUND = NO LO ENCONTRO
        }
    }
    //----------------------BUSCAR POR NOMBRE PACIENTE  ------------------------

    @GetMapping("nombre/{nombre}")
    public ResponseEntity<Paciente> buscarPacientePorNombre(@PathVariable String nombre)throws  ResourceNotFoundException{
        Optional<Paciente> pacienteBuscado = pacienteService.buscarPacienteXNombre(nombre);
        if(pacienteBuscado.isPresent()){
            return ResponseEntity.ok(pacienteBuscado.get());
        }else{
            throw new ResourceNotFoundException("Odontologo buscado por nombre es inexistente");
            //return ResponseEntity.notFound().build();
        }
    }


}
