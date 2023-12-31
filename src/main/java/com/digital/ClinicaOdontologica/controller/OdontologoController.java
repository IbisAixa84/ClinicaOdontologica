package com.digital.ClinicaOdontologica.controller;
import com.digital.ClinicaOdontologica.entities.Odontologo;
import com.digital.ClinicaOdontologica.exception.BadRequestException;
import com.digital.ClinicaOdontologica.exception.ResourceNotFoundException;
import com.digital.ClinicaOdontologica.service.OdontologoService;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/odontologos")
public class OdontologoController {

    @Autowired
    private OdontologoService odontologoService;

    Logger logger = Logger.getLogger(OdontologoController.class);

    //---------------------POST GUARDAR ODONTOLOGO  --------------------------------
    @PostMapping
    public ResponseEntity<Odontologo> guardarOdontologo(@RequestBody Odontologo odontologo) {
        Odontologo odontologoCreado= odontologoService.guardarOdontologo(odontologo);
        logger.log(Level.INFO,"Odontologo creado");
        return  ResponseEntity.ok(odontologoCreado);
    }

    // ------------------------GET LISTAR ----------------------------------------
    @GetMapping("/listar")
    public ResponseEntity <List<Odontologo>> listarOdontologos()throws BadRequestException {
        List<Odontologo> odontologos = odontologoService.listarOdontologos();
        if(odontologos.isEmpty()){
            throw new BadRequestException("No se puede listar odontologos, no hay datos");
            //return ResponseEntity.badRequest().build();
        }else{
            logger.log(Level.INFO,"Listando odontologos...");
            return ResponseEntity.ok(odontologos);
        }
    }

    //----------------------GET BUSCAR POR ID  ------------------------
    @GetMapping("/{id}")
    public ResponseEntity<Odontologo> buscarOdontologoPorID (@PathVariable Long id)throws ResourceNotFoundException{
        Optional<Odontologo> odontologoBuscado = odontologoService.buscarOdontologoPorId(id);
        if(odontologoBuscado.isPresent()){
            logger.log(Level.INFO,"Odontologo con id= " + id + " fue encontrado");
            return ResponseEntity.ok(odontologoBuscado.get());
        }else{
            throw new ResourceNotFoundException("No se encuentra id: "+id+" en la base de datos");
            //return ResponseEntity.notFound().build();
        }
    }

    //----------------------DELETE ODONTOLOGOS  ------------------------
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarOdontologo(@PathVariable Long id)throws ResourceNotFoundException {
        Optional<Odontologo> odontologoBuscado = odontologoService.buscarOdontologoPorId(id);
        if(odontologoBuscado.isPresent()){
            odontologoService.eliminarOdontologo(id);
            logger.log(Level.INFO,"Odontologo con id= " + id + " fue eliminado");
            return ResponseEntity.ok("El Odontologo con id: "+ id+" fue eliminado correctamente");
        }else {
            throw new ResourceNotFoundException("El odontologo con id: "+id+" no se encuentra registrado para ser eliminado.");
            //return ResponseEntity.badRequest().body("El odontologo con id: "+id+" no se encuentra registrado para ser eliminado.");
        }
    }

    //----------------------ACTUALIZAR ODONTOLOGOS  ------------------------
    @PutMapping
    public ResponseEntity<String> actualizarOdontologo(@RequestBody Odontologo odontologo)throws BadRequestException{
        Optional<Odontologo> odontologoBuscado = odontologoService.buscarOdontologoPorId(odontologo.getId());
        if(odontologoBuscado.isPresent()){
            odontologoService.actualizarOdontologo(odontologo);
            logger.log(Level.INFO,"Odontologo con id= " + odontologo.getId() + " fue actualizado correctamente.");
            return ResponseEntity.ok("El odontologo con id: "+ odontologo.getId()+ " fue actualizado correctamente.");
        }else {
            throw new BadRequestException("El odontologo con id : "+odontologo.getId()+" no se encuentra para se modificado.");
            //return ResponseEntity.badRequest().body("El odontologo con id : "+odontologo.getId()+" no se encuentra para se modificado.");

        }
    }
    //----------------------BUSCAR POR NOMBRE ODONTOLOGOS  ------------------------

    @GetMapping("nombre/{nombre}")
    public ResponseEntity<Odontologo> buscarOdontologoPorNombre(@PathVariable String nombre)throws  ResourceNotFoundException{
        Optional<Odontologo> odontologoBuscado = odontologoService.buscarOdontologoXNombre(nombre);
        if(odontologoBuscado.isPresent()){
            logger.log(Level.INFO,"Odontologo con nombre= " + nombre + " fue encontrado.");
            return ResponseEntity.ok(odontologoBuscado.get());
        }else{
            throw new ResourceNotFoundException("Odontologo buscado por nombre es inexistente");
            //return ResponseEntity.notFound().build();
        }
    }
}
