package com.example.clase23.controller;

import com.example.clase23.entities.Paciente;
import com.example.clase23.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {
   @Autowired
    private PacienteService pacienteService;

    @PostMapping
    public ResponseEntity<Paciente> registrarPaciente(@RequestBody Paciente paciente){
        return ResponseEntity.ok(pacienteService.guardarPaciente(paciente));
    }
    @GetMapping("/{id}")
    public ResponseEntity<Paciente> buscarPaciente(@PathVariable Long id){
        Optional<Paciente> pacienteBuscado= pacienteService.buscarPacientePorID(id);
        if (pacienteBuscado.isPresent()){
            return ResponseEntity.ok(pacienteBuscado.get());
        }else{
            return ResponseEntity.notFound().build();
        }


    }
    @PutMapping
    public ResponseEntity<String> actualizarPaciente(@RequestBody Paciente paciente){
        //vamos a consultar si ese paciente existe
        Optional<Paciente> pacientebuscado= pacienteService.buscarPacientePorID(paciente.getId());
        if(pacientebuscado.isPresent()){
            pacienteService.actualizarPaciente(paciente);
            return ResponseEntity.ok("Paciente Actualizado -"+paciente.getNombre()+" "+paciente.getApellido());
        }
        else{
            return ResponseEntity.badRequest().body("Paciente No Encontrado"+paciente.getId()+"Nombre: "+paciente.getNombre());
        }

    }
    @GetMapping("/email/{correo}")
    public ResponseEntity<Paciente> buscarPacientePorCorreo(@PathVariable String correo){
        Optional<Paciente> pacienteBuscado= pacienteService.buscarPacientePorCorreo(correo);
        if(pacienteBuscado.isPresent()){
            return ResponseEntity.ok(pacienteBuscado.get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }
}
