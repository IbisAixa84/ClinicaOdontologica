package com.digital.ClinicaOdontologica.service;

import com.digital.ClinicaOdontologica.entities.Paciente;
import com.digital.ClinicaOdontologica.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import java.util.List;
import java.util.Optional;

@Service
public class PacienteService {
    @Autowired
    private PacienteRepository pacienteRepository;

    public Paciente guardarPaciente(Paciente paciente){
        return pacienteRepository.save(paciente);
    }
    public Optional<Paciente> buscarPacientePorID(Long id){
        return pacienteRepository.findById(id);
    }
    public void eliminarPaciente(Long id){
        pacienteRepository.deleteById(id);
    }
    public void actualizarPaciente(Paciente paciente){
        pacienteRepository.save(paciente);
    }
    public List<Paciente> devolverPacientes(){
        return pacienteRepository.findAll();
    }
    public Optional<Paciente> buscarPacientePorCorreo(String email){
        return pacienteRepository.findByEmail(email);
    }

    public Optional<Paciente> buscarPacienteXNombre(String palabra){
        return pacienteRepository.findBynombreLike(palabra);
    }

}
